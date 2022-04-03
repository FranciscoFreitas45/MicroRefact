package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.InstituteService;
public class InstituteServiceImpl implements InstituteService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Institute> getall(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getall"))
;  List<Institute> aux = restTemplate.getForObject(builder.toUriString(), List<Institute>.class);

 return aux;
}


public Institute find(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("name",name)
;  Institute aux = restTemplate.getForObject(builder.toUriString(), Institute.class);

 return aux;
}


}