package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaUserDao;
import com.ushahidi.swiftriver.core.model.User;
@Service
public class UserAccountService {

@Autowired
 private JpaUserDao jpauserdao;


public User getOwner(long idHR3O){
return jpauserdao.getOwner(idHR3O);
}


public void setOwner(long idHR3O,User owner){
jpauserdao.setOwner(idHR3O,owner);
}


}