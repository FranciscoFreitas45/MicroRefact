package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Account;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
public class AccountRequestImpl implements AccountRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Account getAccount(long idXN57){
 Account aux = restTemplate.getForObject("http://7/River/{id}/Account/getAccount",Account.class,idXN57);
return aux;
}


public void setAccount(Account account,long idXN57){
 restTemplate.put("http://7/River/{id}/Account/setAccount",account,idXN57);
 return ;
}


}