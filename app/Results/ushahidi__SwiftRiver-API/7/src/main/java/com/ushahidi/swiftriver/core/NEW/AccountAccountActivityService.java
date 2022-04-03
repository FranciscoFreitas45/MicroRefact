package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountAccountActivityService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getActionOnObj(long idD2H4){
return jpaaccountdao.getActionOnObj(idD2H4);
}


public void setActionOnObj(long idD2H4,Account actionOnObj){
jpaaccountdao.setActionOnObj(idD2H4,actionOnObj);
}


}