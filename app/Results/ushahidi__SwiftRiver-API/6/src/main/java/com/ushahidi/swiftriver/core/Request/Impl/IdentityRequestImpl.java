package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Identity;
import com.ushahidi.swiftriver.core.Request.IdentityRequest;
public class IdentityRequestImpl implements IdentityRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setIdentity(Identity identity,long idYKYP){
 restTemplate.put("http://0/Drop/{id}/Identity/setIdentity",identity,idYKYP);
 return ;
}


public Identity getIdentity(long idYKYP){
 Identity aux = restTemplate.getForObject("http://0/Drop/{id}/Identity/getIdentity",Identity.class,idYKYP);
return aux;
}


}