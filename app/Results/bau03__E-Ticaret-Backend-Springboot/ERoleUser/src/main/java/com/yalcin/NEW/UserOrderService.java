package com.yalcin.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.repository.UserRepository;
import com.yalcin.entity.User;
@Service
public class UserOrderService {

@Autowired
 private UserRepository userrepository;


public User getUser(Integer id){
return userrepository.getUser(id);
}


public void setUser(Integer id,User user){
userrepository.setUser(id,user);
}


}