package com.fzshuai.dao;
 import com.fzshuai.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>{


public User findByUsernameAndPassword(String username,String password)
;

public User getUser(Long id);

public void setUser(Long id,User user);

}