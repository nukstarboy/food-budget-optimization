package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.AdminDetail;
import project.models.TrialMode;
import project.service.AdminService;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{emailId}")
    public AdminDetail get(@PathVariable String emailId) {
        return this.adminService.get(emailId);
    }

    @PostMapping("/dueOn")
    public AdminDetail updateDueOn(@RequestBody TrialMode trialMode) {
        return this.adminService.updateDueOn(trialMode);
    }

    @PostMapping("/saveAdmin")
    public AdminDetail saveAdminDetail(@RequestBody AdminDetail adminDetail) {
        return this.adminService.saveAdminDetail(adminDetail);
    }

    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody AdminDetail adminDetail) {
        return this.adminService.login(adminDetail);
    }

    @GetMapping("/getAdminData/{adminId}")
    public List<AdminDetail> getAdminData(@PathVariable int adminId, @RequestHeader("Authorization") String authorizationToken) {
        return this.adminService.getAdminData(authorizationToken, adminId);
    }

}