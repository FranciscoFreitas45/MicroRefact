package com.fzshuai.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.Interface.TypeService;
public class TypeServiceImpl implements TypeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Type> listTypeTop(Integer size){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listTypeTop"))
    .queryParam("size",size)
;  List<Type> aux = restTemplate.getForObject(builder.toUriString(), List<Type>.class);

 return aux;
}


}