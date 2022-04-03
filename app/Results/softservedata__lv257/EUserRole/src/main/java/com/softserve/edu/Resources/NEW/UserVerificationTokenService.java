package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.UserDAOImpl;
import com.softserve.edu.Resources.entity.User;
@Service
public class UserVerificationTokenService {

@Autowired
 private UserDAOImpl userdaoimpl;


public User getUser(Long idXLHJ){
return userdaoimpl.getUser(idXLHJ);
}


public VerificationToken setUser(Long idXLHJ,User user){
return userdaoimpl.setUser(idXLHJ,user);
}


}