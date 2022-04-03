package com.cg.oms.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.repository.UserRepository;
import com.cg.oms.model.User;
@Service
public class UserCartService {

@Autowired
 private UserRepository userrepository;


public User getUser(Long userId){
return userrepository.getUser(userId);
}


public void setUser(Long userId,User user){
userrepository.setUser(userId,user);
}


}