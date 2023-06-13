package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.config.Config;
import project.models.AdminDetail;
import project.models.Token;
import project.models.TrialMode;
import project.repo.AdminRepo;
import project.utils.GenerateToken;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AdminService {
    private final AdminRepo adminRepo;
    private final TokenService tokenService;
    private final Config config;

    @Autowired
    public AdminService(AdminRepo adminRepo, TokenService tokenService, Config config) {
        this.adminRepo = adminRepo;
        this.tokenService = tokenService;
        this.config = config;
    }

    public AdminDetail get(String emailId) {
        return adminRepo.getAdminDetailByEmailId(emailId);
    }

    public AdminDetail saveAdminDetail(AdminDetail adminDetail) {
        PasswordEncoder encodedPassword = config.encodedPassword();
        adminDetail.setPassword(encodedPassword.encode(adminDetail.getPassword()));
        return adminRepo.save(adminDetail);
    }

    public AdminDetail adminLogin(String emailId, String password) {
        AdminDetail adminDetail = get(emailId);
        PasswordEncoder encodedPassword = config.encodedPassword();
        if (adminDetail != null) {
            if (encodedPassword.matches(password, adminDetail.getPassword())) {
                return adminDetail;
            }
        }

        return null;
    }

    public Token login(AdminDetail adminDetail) {
        GenerateToken generateToken = new GenerateToken();
        HttpHeaders httpHeader = null;

        AdminDetail loginUser = this.adminLogin(adminDetail.getEmailId(), adminDetail.getPassword());

        if (loginUser != null) {
            String tokenData[] = generateToken.createJWT(adminDetail.getEmailId(),
                    "JavaTpoint",
                    "JWT Token",
                    300000);
            String token = tokenData[0];

            boolean userEmailExists = tokenService.getTokenDetail(adminDetail.getEmailId());
            Token buildToken = new Token(loginUser.getAdminID(), token, tokenData[1], loginUser.getEmailId(), HttpStatus.OK.toString());
            if (userEmailExists) {
                tokenService.updateToken(adminDetail.getEmailId(), token, tokenData[1]);
                return buildToken;
            } else {
                tokenService.saveUserEmail(buildToken);
                return buildToken;
            }
        } else {
            return new Token(HttpStatus.NOT_FOUND.toString());
        }
    }

    public List<AdminDetail> getAdminData(String authorizationToken, int adminId) {
        String token[] = authorizationToken.split(" ");
        Token getToken = tokenService.tokenAuthentication(adminId, token[1]);

        if (getToken != null) {
            return StreamSupport.stream(adminRepo.findAll().spliterator(), false).toList();
        } else {
            return null;
        }
    }

    public AdminDetail updateDueOn(TrialMode trialMode) {
        AdminDetail adminDetail = this.get(trialMode.getEmailId());
        adminDetail.setDueOn(trialMode.getDueOn());
        return this.adminRepo.save(adminDetail);
    }
}
