package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Token;
import project.repo.TokenRepo;


@Service
public class TokenService {

    private final TokenRepo tokenRepo;

    @Autowired
    public TokenService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    public void saveUserEmail(String email, int adminId) {
        Token token = new Token(adminId, email);
        tokenRepo.save(token);
    }

    public void updateToken(String email, String authenticationToken, String secretKey) {
        Token token = tokenRepo.getTokenByEmailId(email);
        token.setAuthenticationToken(authenticationToken);
        token.setSecretKey(secretKey);

        tokenRepo.save(token);
    }

    public boolean getTokenDetail(String email) {
        return tokenRepo.existsTokenByEmailId(email);
    }

    public Token tokenAuthentication(int userId, String token) {
        return tokenRepo.getTokenByUserIDAndAndAuthenticationToken(userId, token);
    }

}
