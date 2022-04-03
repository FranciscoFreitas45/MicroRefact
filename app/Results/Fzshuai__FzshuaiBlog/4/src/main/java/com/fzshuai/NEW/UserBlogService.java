package com.fzshuai.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.dao.UserRepository;
import com.fzshuai.po.User;
@Service
public class UserBlogService {

@Autowired
 private UserRepository userrepository;


public User getUser(Long id){
return userrepository.getUser(id);
}


public void setUser(Long id,User user){
userrepository.setUser(id,user);
}


}