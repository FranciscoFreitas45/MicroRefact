package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.DivisionService;
public class DivisionServiceImpl implements DivisionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<Division> getallOfParticularClass(Classes classes){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getallOfParticularClass"))
    .queryParam("classes",classes)
;  List<Division> aux = restTemplate.getForObject(builder.toUriString(), List<Division>.class);

 return aux;
}


public void create(Division div){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("div",div)
;
  restTemplate.put(builder.toUriString(), null);
}


public void update(Division classes){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("classes",classes)
;
  restTemplate.put(builder.toUriString(), null);
}


public void delet(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delet"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


public Division find(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("id",id)
;  Division aux = restTemplate.getForObject(builder.toUriString(), Division.class);

 return aux;
}


}