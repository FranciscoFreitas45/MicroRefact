package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.AdminService;
public class AdminServiceImpl implements AdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String getCurrentUsername(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrentUsername"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}