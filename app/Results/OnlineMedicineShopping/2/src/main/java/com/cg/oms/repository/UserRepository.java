package com.cg.oms.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.oms.model.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface UserRepository extends JpaRepository<User, Long>{


public User findUserByEmailId(String emailId)
;
@Query(value = "Select * from user_table t  where t.user_id = ?1", nativeQuery = true)
public User getUser(Long userId);

@Transactional
@Modifying
@Query(value = "update user_table t set t.user_id = ?1 where t.user_id = ?1", nativeQuery = true)
public void setUser(Long userId,User user);
@Query(value = "Select * from user_table t  where t.user_id = ?1", nativeQuery = true)
public User getUser2(Long userId);

@Transactional
@Modifying
@Query(value = "update user_table t set t.user_id = ?1 where t.user_id = ?1", nativeQuery = true)
public void setUser2(Long userId,User user);

}