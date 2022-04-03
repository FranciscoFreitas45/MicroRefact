package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.AdressRepository;
public class AdressRepositoryImpl implements AdressRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Adress findAllById(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllById"))
    .queryParam("id",id)
;  Adress aux = restTemplate.getForObject(builder.toUriString(), Adress.class);

 return aux;
}


}