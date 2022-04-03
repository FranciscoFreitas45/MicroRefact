package io.swagger.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.DTO.Provider;
import io.swagger.Request.ProviderRequest;
public class ProviderRequestImpl implements ProviderRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setProvider(Provider provider,Long id){
 restTemplate.put("http://1/Pet/{id}/Provider/setProvider",provider,id);
 return ;
}


public Provider getProvider(Long id){
 Provider aux = restTemplate.getForObject("http://1/Pet/{id}/Provider/getProvider",Provider.class,id);
return aux;
}


}