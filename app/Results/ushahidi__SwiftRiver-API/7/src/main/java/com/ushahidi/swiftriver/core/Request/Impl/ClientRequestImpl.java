package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.Client;
import com.ushahidi.swiftriver.core.Request.ClientRequest;
public class ClientRequestImpl implements ClientRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Set<Client> getClients(long id){
 Set<Client> aux = restTemplate.getForObject("http://0/Account/{id}/Client/getClients",Set<Client>.class,id);
return aux;
}


public void setClients(Set<Client> clients,long id){
 restTemplate.put("http://0/Account/{id}/Client/setClients",clients,id);
 return ;
}


}