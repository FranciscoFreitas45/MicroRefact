package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.UserDetailsDAOImpl;
import com.softserve.edu.Resources.entity.UserDetails;
@Service
public class UserDetailsUserService {

@Autowired
 private UserDetailsDAOImpl userdetailsdaoimpl;


public User setUserDetails(Long idO4D4,UserDetails userDetails){
return userdetailsdaoimpl.setUserDetails(idO4D4,userDetails);
}


public UserDetails getUserDetails(Long idO4D4){
return userdetailsdaoimpl.getUserDetails(idO4D4);
}


}