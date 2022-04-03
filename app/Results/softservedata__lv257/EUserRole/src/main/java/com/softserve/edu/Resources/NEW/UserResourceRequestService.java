package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.UserDAOImpl;
import com.softserve.edu.Resources.entity.User;
@Service
public class UserResourceRequestService {

@Autowired
 private UserDAOImpl userdaoimpl;


public ResourceRequest setResourcesAdmin(Long idSND5,User resourcesAdmin){
return userdaoimpl.setResourcesAdmin(idSND5,resourcesAdmin);
}


public ResourceRequest setRegister(Long idSND5,User register){
return userdaoimpl.setRegister(idSND5,register);
}


public User getRegister(Long idSND5){
return userdaoimpl.getRegister(idSND5);
}


public User getResourcesAdmin(Long idSND5){
return userdaoimpl.getResourcesAdmin(idSND5);
}


}