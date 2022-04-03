package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.User;
import java.util.List;
import java.util.Optional;
public interface UserDAO {


public List<User> getAllUsers()
;

public Optional<User> findById(Long id)
;

public void makeTransient(User user)
;

public User makePersistent(User user)
;

public List<User> getUsersWithRoles(String roleNames)
;

public User findByEmail(String email)
;

}