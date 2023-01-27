package project.repo;

public interface TokenRepo {
    void saveUserEmail(String email, int adminId);

    Boolean updateToken(String email, String authenticationToken, String secretKey);

    Integer getTokenDetail(String email);

    Integer tokenAuthentication(String token, int emailId);
}
