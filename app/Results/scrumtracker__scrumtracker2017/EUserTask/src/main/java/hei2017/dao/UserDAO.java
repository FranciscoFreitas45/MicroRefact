package hei2017.dao;
 import hei2017.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;
public interface UserDAO extends JpaRepository<User, Long>{


public User findOneById(Long id)
;

public long count()
;

public Set<User> findByUserTasksId(Long id)
;

public User findOneByEmail(String email)
;

public User findOneByNomAndPrenom(String nom,String prenom)
;

public User findOneByPseudo(String pseudo)
;

public void setUserTasks(Long id,Set<Task> userTasks);

}