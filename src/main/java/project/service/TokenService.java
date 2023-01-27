package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.repo.TokenRepoImpl;

import javax.transaction.Transactional;

@Service
public class TokenService {

    private final TokenRepoImpl tokenRepo;

    @Autowired
    public TokenService(TokenRepoImpl tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Transactional
    public void saveUserEmail(String email, int adminId) {
        tokenRepo.saveUserEmail(email, adminId);
    }

    @Transactional
    public boolean updateToken(String email, String authenticationToken, String secretKey) {
        return tokenRepo.updateToken(email, authenticationToken, secretKey);
    }

    @Transactional
    public int getTokenDetail(String email) {
        return tokenRepo.getTokenDetail(email);
    }

    @Transactional
    public int tokenAuthentication(String token, int emailId) {
        return tokenRepo.tokenAuthentication(token, emailId);
    }

}
