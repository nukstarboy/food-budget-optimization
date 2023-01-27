package project.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.models.AdminDetail;

import java.util.List;

@Repository
public class AdminRepoImpl {

    // Autowired SessionFactory Object So that we can get session object used for interaction with Database.
    private final SessionFactory sessionFactory;

    @Autowired
    public AdminRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer adminLogin(String emailId, String password) {

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("from AdminDetail where emailId=:emailId and password=:password");
            query.setParameter("emailId", emailId);
            query.setParameter("password", password);
            List<AdminDetail> list = query.list();

            int size = list.size();
            if (size == 1) {
                return list.get(0).getAdminID();
            } else {
                return -1;
            }
        } catch (Exception exception) {
            System.out.println("Excecption while saving admin Details : " + exception.getMessage());
            return 0;
        } finally {
            session.flush();
        }

    }


}
