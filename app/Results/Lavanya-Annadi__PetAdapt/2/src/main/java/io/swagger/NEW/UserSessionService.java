package io.swagger.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.repository.UserRepository;
import io.swagger.model.User;
@Service
public class UserSessionService {

@Autowired
 private UserRepository userrepository;


public User getUser(Long id){
return userrepository.getUser(id);
}


public void setUser(Long id,User user){
userrepository.setUser(id,user);
}


}