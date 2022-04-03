package main.repository;
 import main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


public User findByCode(String code)
;

public User findByName(String name)
;

@Query(value = "select is_moderator from users where id = :query", nativeQuery = true)
public int isAdmin(int id)
;

public User findByEmail(String email)
;

}