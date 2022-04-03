package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.UserDAOImpl;
import com.softserve.edu.Resources.entity.User;
@Service
public class UserUserDetailsService {

@Autowired
 private UserDAOImpl userdaoimpl;


public User getUser(Long idO2KS){
return userdaoimpl.getUser(idO2KS);
}


public void setUser(Long idO2KS,User user){
userdaoimpl.setUser(idO2KS,user);
}


}