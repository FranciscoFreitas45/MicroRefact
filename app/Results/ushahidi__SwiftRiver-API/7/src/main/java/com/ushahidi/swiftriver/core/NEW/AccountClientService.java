package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountClientService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long id159A){
return jpaaccountdao.getAccount(id159A);
}


public void setAccount(long id159A,Account account){
jpaaccountdao.setAccount(id159A,account);
}


}