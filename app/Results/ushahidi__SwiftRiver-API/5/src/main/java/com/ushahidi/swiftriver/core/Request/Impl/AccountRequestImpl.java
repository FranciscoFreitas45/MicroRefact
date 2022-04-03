package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Account;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
public class AccountRequestImpl implements AccountRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Account getActionOnObj(long idD2H4){
 Account aux = restTemplate.getForObject("http://7/AccountActivity/{id}/Account/getActionOnObj",Account.class,idD2H4);
return aux;
}


public void setActionOnObj(Account actionOnObj,long idD2H4){
 restTemplate.put("http://7/AccountActivity/{id}/Account/setActionOnObj",actionOnObj,idD2H4);
 return ;
}


}