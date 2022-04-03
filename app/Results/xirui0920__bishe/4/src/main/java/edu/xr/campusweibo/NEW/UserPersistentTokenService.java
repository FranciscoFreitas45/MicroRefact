package edu.xr.campusweibo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.repository.UserRepository;
import edu.xr.campusweibo.domain.User;
@Service
public class UserPersistentTokenService {

@Autowired
 private UserRepository userrepository;


public User getUser(Long id){
return userrepository.getUser(id);
}


public void setUser(Long id,User user){
userrepository.setUser(id,user);
}


}