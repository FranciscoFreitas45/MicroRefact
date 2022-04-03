package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.UserRepository;
import com.gp.cricket.entity.User;
@Service
public class UserSponsorService {

@Autowired
 private UserRepository userrepository;


public User getUserId(Integer userIdv2){
return userrepository.getUserId(userIdv2);
}


public void setUserId(Integer userIdv2,User userId){
userrepository.setUserId(userIdv2,userId);
}


}