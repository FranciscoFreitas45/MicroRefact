package edu.xr.campusweibo.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.DTO.PersistentToken;
import edu.xr.campusweibo.Request.PersistentTokenRequest;
public class PersistentTokenRequestImpl implements PersistentTokenRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setPersistentTokens(Set<PersistentToken> persistentTokens,Long id){
 restTemplate.put("http://5/User/{id}/PersistentToken/setPersistentTokens",persistentTokens,id);
 return ;
}


public Set<PersistentToken> getPersistentTokens(Long id){
 Set<PersistentToken> aux = restTemplate.getForObject("http://5/User/{id}/PersistentToken/getPersistentTokens",Set<PersistentToken>.class,id);
return aux;
}


}