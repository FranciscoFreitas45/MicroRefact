package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountFormService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long idQGFJ){
return jpaaccountdao.getAccount(idQGFJ);
}


public void setAccount(long idQGFJ,Account account){
jpaaccountdao.setAccount(idQGFJ,account);
}


}