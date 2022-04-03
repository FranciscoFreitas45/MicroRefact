package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.InStorageRepository;
public class InStorageRepositoryImpl implements InStorageRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Float findInStorageOrderByDay(String dateString){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findInStorageOrderByDay"))
    .queryParam("dateString",dateString)
;  Float aux = restTemplate.getForObject(builder.toUriString(), Float.class);

 return aux;
}


}