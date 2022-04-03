package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountActivityService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getActionTo(long idTMNI){
return jpaaccountdao.getActionTo(idTMNI);
}


public void setActionTo(long idTMNI,Account actionTo){
jpaaccountdao.setActionTo(idTMNI,actionTo);
}


public Account getAccount(long idTMNI){
return jpaaccountdao.getAccount(idTMNI);
}


public void setAccount(long idTMNI,Account account){
jpaaccountdao.setAccount(idTMNI,account);
}


}