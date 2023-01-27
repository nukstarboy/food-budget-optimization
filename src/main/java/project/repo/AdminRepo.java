package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.AdminDetail;

public interface AdminRepo extends CrudRepository<AdminDetail, Integer> {
//    @Query(value = "from AdminDetail where emailId=:emailId and password=:password", nativeQuery = true)
    AdminDetail getAdminDetailByEmailIdAndPassword(String emailId, String password);
}
