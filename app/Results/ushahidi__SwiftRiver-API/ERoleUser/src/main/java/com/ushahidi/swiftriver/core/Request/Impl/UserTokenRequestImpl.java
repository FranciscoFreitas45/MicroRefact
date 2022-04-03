package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.UserToken;
import com.ushahidi.swiftriver.core.Request.UserTokenRequest;
public class UserTokenRequestImpl implements UserTokenRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<UserToken> getTokens(long id){
 Set<UserToken> aux = restTemplate.getForObject("http://9/User/{id}/UserToken/getTokens",Set<UserToken>.class,id);
return aux;
}


public void setTokens(Set<UserToken> tokens,long id){
 restTemplate.put("http://9/User/{id}/UserToken/setTokens",tokens,id);
 return ;
}


}