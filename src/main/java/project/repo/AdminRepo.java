package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.AdminDetail;

public interface AdminRepo extends CrudRepository<AdminDetail, Integer> {
    AdminDetail getAdminDetailByEmailIdAndPassword(String emailId, String password);

    AdminDetail getAdminDetailByEmailId(String emailId);
}
