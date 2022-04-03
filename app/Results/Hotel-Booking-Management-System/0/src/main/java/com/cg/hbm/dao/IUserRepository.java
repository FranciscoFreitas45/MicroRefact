package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.hbm.entites.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{


@Query(value = "Select * from user u  where u.user_id = ?1", nativeQuery = true)
public User getUser(int user_id);

@Transactional
@Modifying
@Query(value = "update user u set u.user_id = ?1 where u.user_id = ?1", nativeQuery = true)
public void setUser(int user_id,User user);

}