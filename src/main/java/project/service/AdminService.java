package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.AdminDetail;
import project.repo.AdminRepo;
import project.repo.AdminRepoImpl;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AdminService {

    private final AdminRepoImpl adminRepoImpl;
    private final AdminRepo adminRepo;

    @Autowired
    public AdminService(AdminRepoImpl adminRepoImpl, AdminRepo adminRepo) {
        this.adminRepoImpl = adminRepoImpl;
        this.adminRepo = adminRepo;
    }

    @Transactional
    public AdminDetail saveAdminDetail(AdminDetail adminDetail) {
        return adminRepo.save(adminDetail);
    }

    @Transactional
    public int adminLogin(String emailId, String password) {
        return adminRepo.getAdminDetailByEmailIdAndPassword(emailId, password).getAdminID();
    }

    @Transactional
    public List<AdminDetail> getAdminData() {
        return StreamSupport.stream(adminRepo.findAll().spliterator(), false).toList();
    }



}
