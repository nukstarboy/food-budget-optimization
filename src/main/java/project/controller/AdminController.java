package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.AdminDetail;
import project.service.AdminService;
import project.service.GenerateToken;
import project.service.TokenService;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService adminService;
    private final TokenService tokenService;

    GenerateToken generateToken = new GenerateToken();

    @Autowired
    public AdminController(AdminService adminService, TokenService tokenService) {
        this.adminService = adminService;
        this.tokenService = tokenService;
    }

    @PostMapping("/saveAdmin")
    public AdminDetail saveAdminDetail(@RequestBody AdminDetail adminDetail) {
        return adminService.saveAdminDetail(adminDetail);
    }

    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody AdminDetail adminDetail) {
        int status;
        HttpHeaders httpHeader = null;

        // Authenticate User.
        status = adminService.adminLogin(adminDetail.getEmailId(), adminDetail.getPassword());

        /*
         * If User is authenticated then Do Authorization Task.
         */
        if (status > 0) {
            /*
             * Generate token.
             */
            String tokenData[] = generateToken.createJWT(adminDetail.getEmailId(), "JavaTpoint", "JWT Token",
                    adminDetail.getRole(), 43200000);

            // get Token.
            String token = tokenData[0];

            System.out.println("Authorization :: " + token);

            // Create the Header Object
            httpHeader = new HttpHeaders();

            // Add token to the Header.
            httpHeader.add("Authorization", token);

            // Check if token is already exist.
            long isUserEmailExists = tokenService.getTokenDetail(adminDetail.getEmailId());

            /*
             * If token exist then update Token else create and insert the token.
             */
            if (isUserEmailExists > 0) {
                tokenService.updateToken(adminDetail.getEmailId(), token, tokenData[1]);
            } else {
                tokenService.saveUserEmail(adminDetail.getEmailId(), status);
                tokenService.updateToken(adminDetail.getEmailId(), token, tokenData[1]);
            }

            return new ResponseEntity<Integer>(status, httpHeader, HttpStatus.OK);
        }

        // if not authenticated return  status what we get.
        else {
            return new ResponseEntity<Integer>(status, httpHeader, HttpStatus.OK);
        }


    }


    @GetMapping("/getAdminData/{adminId}")
    public List<AdminDetail> getAdminData(@PathVariable int adminId, @RequestHeader("Authorization") String authorizationToken) {
        String token[] = authorizationToken.split(" ");
        int result = tokenService.tokenAuthentication(token[1], adminId);

        if (result > 0) {
            return adminService.getAdminData();
        } else {
            return null;
        }
    }

}