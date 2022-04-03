package com.example.smartkitchenbackend.repositories.user;
 import com.example.smartkitchenbackend.entities.User;
import java.util.List;
public interface UserRepository {


public User findByUsernameOrEmail(String username,String email)
;

public User findById(long userId)
;

public void save(User user)
;

public Boolean existsByUsername(String username)
;

public Boolean existsByEmail(String email)
;

public List<User> findAll()
;

public User getReference(long userId)
;

}