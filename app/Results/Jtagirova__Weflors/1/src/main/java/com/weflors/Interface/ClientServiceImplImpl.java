package com.weflors.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.weflors.Interface.ClientServiceImpl;
public class ClientServiceImplImpl implements ClientServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<ClientEntity> findAllClients(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllClients"))
;  List<ClientEntity> aux = restTemplate.getForObject(builder.toUriString(), List<ClientEntity>.class);

 return aux;
}


}