package com.evolvingreality.onleave.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.repository.UserRepository;
import com.evolvingreality.onleave.model.User;
@Service
public class UserSecurityGroupMemberService {

@Autowired
 private UserRepository userrepository;


public User getUser(Long id){
return userrepository.getUser(id);
}


public void setUser(Long id,User user){
userrepository.setUser(id,user);
}


}