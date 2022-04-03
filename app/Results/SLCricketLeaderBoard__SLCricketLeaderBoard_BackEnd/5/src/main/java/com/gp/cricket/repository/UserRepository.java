package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.User;
public interface UserRepository extends JpaRepository<User, Integer>{


@Query("from User u where u.status=1")
public List<User> getActiveUsers()
;

public User findUserByNic(String nic)
;

@Query(value = "SELECT * FROM user u WHERE (u.nic = :nic OR u.email = :email) AND (u.user_id NOT IN (:userId)) LIMIT 1 ", nativeQuery = true)
public User findByNicAndEmail(String updateNic,String updateEmail,Integer userId)
;

public User findUserByUserName(String userName)
;

@Query("FROM User u WHERE u.userId = :userId")
public User findByUserId(Integer userId)
;

public User findUserByEmail(String email)
;

public User findByEmail(String email)
;

public User getUserId(Integer userIdv2);

public void setUserId(Integer userIdv2,User userId);

public User getUserId(Integer userIdv2);

public void setUserId(Integer userIdv2,User userId);

public User getUserId(Integer userIdv2);

public void setUserId(Integer userIdv2,User userId);

public void setStatus(Integer id,Byte status);

}