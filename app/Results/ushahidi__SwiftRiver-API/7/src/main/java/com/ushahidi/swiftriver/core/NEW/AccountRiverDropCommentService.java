package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountRiverDropCommentService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long idG79G){
return jpaaccountdao.getAccount(idG79G);
}


public void setAccount(long idG79G,Account account){
jpaaccountdao.setAccount(idG79G,account);
}


}