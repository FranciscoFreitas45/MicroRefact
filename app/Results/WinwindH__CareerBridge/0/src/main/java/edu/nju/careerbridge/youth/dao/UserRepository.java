package edu.nju.careerbridge.youth.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.nju.careerbridge.youth.model.User;
import javax.transaction.Transactional;
public interface UserRepository extends JpaRepository<User, Integer>{


@Transactional
@Modifying
@Query(value = "UPDATE user u set u.password =:pa WHERE u.phone =:ph", nativeQuery = true)
public int editPassword(String phone,String password)
;

public User findByPhone(String phone)
;

}