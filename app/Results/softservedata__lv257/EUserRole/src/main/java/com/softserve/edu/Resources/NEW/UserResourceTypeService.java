package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.UserDAOImpl;
import com.softserve.edu.Resources.entity.User;
@Service
public class UserResourceTypeService {

@Autowired
 private UserDAOImpl userdaoimpl;


public ResourceType setAssigner(Long id93GK,User assigner){
return userdaoimpl.setAssigner(id93GK,assigner);
}


public User getAssigner(Long id93GK){
return userdaoimpl.getAssigner(id93GK);
}


}