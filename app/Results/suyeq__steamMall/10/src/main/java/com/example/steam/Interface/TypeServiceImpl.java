package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.TypeService;
public class TypeServiceImpl implements TypeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<String> findAllType(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllType"))
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public List<Type> findAllTypes(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllTypes"))
;  List<Type> aux = restTemplate.getForObject(builder.toUriString(), List<Type>.class);

 return aux;
}


}