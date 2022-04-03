package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountBucketDropCommentService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long idLW0Z){
return jpaaccountdao.getAccount(idLW0Z);
}


public void setAccount(long idLW0Z,Account account){
jpaaccountdao.setAccount(idLW0Z,account);
}


}