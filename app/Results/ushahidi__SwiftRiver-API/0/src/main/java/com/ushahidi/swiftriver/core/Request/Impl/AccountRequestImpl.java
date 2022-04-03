package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Account;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
public class AccountRequestImpl implements AccountRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Account getAccount(long id159A){
 Account aux = restTemplate.getForObject("http://7/Client/{id}/Account/getAccount",Account.class,id159A);
return aux;
}


public void setAccount(Account account,long id159A){
 restTemplate.put("http://7/Client/{id}/Account/setAccount",account,id159A);
 return ;
}


}