package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountBucketCommentService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long idVO82){
return jpaaccountdao.getAccount(idVO82);
}


public void setAccount(long idVO82,Account account){
jpaaccountdao.setAccount(idVO82,account);
}


}