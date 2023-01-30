package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.Token;

public interface TokenRepo extends CrudRepository<Token, Integer> {
    Token getTokenByEmailId(String emailId);

    boolean existsTokenByEmailId(String emailId);

    Token getTokenByUserIDAndAndAuthenticationToken(int userId, String authenticationToken);
}
