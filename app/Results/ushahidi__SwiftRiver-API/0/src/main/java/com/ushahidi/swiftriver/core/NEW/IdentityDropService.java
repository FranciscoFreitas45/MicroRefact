package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaIdentityDao;
import com.ushahidi.swiftriver.core.model.Identity;
@Service
public class IdentityDropService {

@Autowired
 private JpaIdentityDao jpaidentitydao;


public void setIdentity(long idYKYP,Identity identity){
jpaidentitydao.setIdentity(idYKYP,identity);
}


public Identity getIdentity(long idYKYP){
return jpaidentitydao.getIdentity(idYKYP);
}


}