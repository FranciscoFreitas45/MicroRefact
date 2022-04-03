package Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
public class PersistentTokenRequestImpl implements PersistentTokenRequest{

@Autowired
 private RestTemplate restTemplate;


public void setPersistentTokens(Set<PersistentToken> persistentTokens,Long id){
 restTemplate.put("http://5/User/{id}/PersistentToken/setPersistentTokens",persistentTokens,id);
 return ;
}


public Set<PersistentToken> getPersistentTokens(Long id){
 Set<PersistentToken> aux = restTemplate.getForObject("http://5/User/{id}/PersistentToken/getPersistentTokens",Set<PersistentToken>.class,id);
return aux;
}


}