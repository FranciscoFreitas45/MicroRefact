package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountBucketCollaboratorService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long idXXQN){
return jpaaccountdao.getAccount(idXXQN);
}


public void setAccount(long idXXQN,Account account){
jpaaccountdao.setAccount(idXXQN,account);
}


}