package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountBucketService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long id0O8K){
return jpaaccountdao.getAccount(id0O8K);
}


public void setAccount(long id0O8K,Account account){
jpaaccountdao.setAccount(id0O8K,account);
}


}