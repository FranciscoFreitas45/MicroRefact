package io.delivery.service;
 import io.delivery.entity.User;
import java.util.List;
public interface UserService {


public User findById(long id)
;

public User deleteUser(long id)
;

public User create(User user)
;

public User updateUser(User user)
;

public List<User> getUserList()
;

public List<User> findByLogin(String login)
;

}