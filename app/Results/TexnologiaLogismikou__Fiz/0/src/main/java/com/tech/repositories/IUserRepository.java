package com.tech.repositories;
 import com.tech.models.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IUserRepository extends JpaRepository<User, Long>{


public User findByUsername(String username)
;

@Modifying
@Query("update User u set u.username = ?1, u.password = ?2, u.enabled = ?3, u.hasRoom = ?4 where u.id = ?4")
public void setUserInfoById(String username,String password,boolean enabled,boolean hasRoom,Long userId)
;

@Modifying
@Query("update User u set u.hasRoom = ?1 where u.id = ?2")
public void setUserRoom(boolean hasRoom,Long userID)
;

public User findByUserid(Long id)
;

public User findByUsernameAndPassword(String username,String password)
;

}