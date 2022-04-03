package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaAccountDao;
import com.ushahidi.swiftriver.core.model.Account;
@Service
public class AccountRiverService {

@Autowired
 private JpaAccountDao jpaaccountdao;


public Account getAccount(long idXN57){
return jpaaccountdao.getAccount(idXN57);
}


public void setAccount(long idXN57,Account account){
jpaaccountdao.setAccount(idXN57,account);
}


}