package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Account;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
public class AccountRequestImpl implements AccountRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Account getAccount(long idLW0Z){
 Account aux = restTemplate.getForObject("http://7/BucketDropComment/{id}/Account/getAccount",Account.class,idLW0Z);
return aux;
}


public void setAccount(Account account,long idLW0Z){
 restTemplate.put("http://7/BucketDropComment/{id}/Account/setAccount",account,idLW0Z);
 return ;
}


}