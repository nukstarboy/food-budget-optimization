package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.models.AdminDetail;
import project.models.Token;
import project.repo.AdminRepo;
import project.utils.GenerateToken;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AdminService {
    private final AdminRepo adminRepo;
    private final TokenService tokenService;

    @Autowired
    public AdminService(AdminRepo adminRepo, TokenService tokenService) {
        this.adminRepo = adminRepo;
        this.tokenService = tokenService;
    }

    public AdminDetail saveAdminDetail(AdminDetail adminDetail) {
        return adminRepo.save(adminDetail);
    }

    public AdminDetail adminLogin(String emailId, String password) {
        return adminRepo.getAdminDetailByEmailIdAndPassword(emailId, password);
    }

    public ResponseEntity<Integer> login(AdminDetail adminDetail) {
        GenerateToken generateToken = new GenerateToken();
        HttpHeaders httpHeader = null;

        AdminDetail loginUser = this.adminLogin(adminDetail.getEmailId(), adminDetail.getPassword());

        if (loginUser != null) {
            String tokenData[] = generateToken.createJWT(adminDetail.getEmailId(),
                    "JavaTpoint",
                    "JWT Token",
                    adminDetail.getRole(),
                    43200000);
            String token = tokenData[0];

            // Create the Header Object
            httpHeader = new HttpHeaders();
            // Add token to the Header.
            httpHeader.add("Authorization", token);

            boolean userEmailExists = tokenService.getTokenDetail(adminDetail.getEmailId());

            if (userEmailExists) {
                tokenService.updateToken(adminDetail.getEmailId(), token, tokenData[1]);
            } else {
                Token buildToken =
                        new Token(loginUser.getAdminID(), token, tokenData[1], loginUser.getEmailId());
                tokenService.saveUserEmail(buildToken);
            }
            return new ResponseEntity<Integer>(1, httpHeader, HttpStatus.OK);
        } else {
            return new ResponseEntity<Integer>(-1, httpHeader, HttpStatus.NOT_FOUND);
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

}
