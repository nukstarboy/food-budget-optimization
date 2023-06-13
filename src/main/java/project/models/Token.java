package project.models;

import javax.persistence.*;

@Entity
@Table(name = "Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private int tokenID;

    @Column(name = "user_id", unique = true)
    private int userID;

    @Column(name = "authenticationToken")
    private String authenticationToken;

    @Column(name = "secretKey")
    private String secretKey;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "httpStatus")
    private String httpStatus;

    public Token() {
    }

    public Token(int userID, String authenticationToken, String secretKey, String emailId, String httpStatus) {
        this.userID = userID;
        this.authenticationToken = authenticationToken;
        this.secretKey = secretKey;
        this.emailId = emailId;
        this.httpStatus = httpStatus;
    }

    public Token(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getTokenID() {
        return tokenID;
    }

    public void setTokenID(int tokenID) {
        this.tokenID = tokenID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "Token [tokenID=" + tokenID + ", userID=" + userID + ", authenticationToken=" + authenticationToken
                + ", secretKey=" + secretKey + ", emailId=" + emailId + "]";
    }


}
