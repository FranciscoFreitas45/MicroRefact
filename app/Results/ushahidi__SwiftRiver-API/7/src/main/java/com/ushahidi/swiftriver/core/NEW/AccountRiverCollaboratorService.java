package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountRiverCollaboratorService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long idWINU){
return jpaaccountdao.getAccount(idWINU);
}


public void setAccount(long idWINU,Account account){
jpaaccountdao.setAccount(idWINU,account);
}


}