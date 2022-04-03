package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.AdminService;
public class AdminServiceImpl implements AdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public Admin getCurrent(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrent"))
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


}