package guru.springframework.services;
 import guru.springframework.domain.User;
public interface UserService {


public User findUserByEmail(String email)
;

public void saveUser(User user)
;

}