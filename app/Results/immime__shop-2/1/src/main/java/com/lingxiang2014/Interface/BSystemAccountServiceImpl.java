package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.BSystemAccountService;
public class BSystemAccountServiceImpl implements BSystemAccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public BSystemAccount findLeaf(Integer index){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLeaf"))
    .queryParam("index",index)
;  BSystemAccount aux = restTemplate.getForObject(builder.toUriString(), BSystemAccount.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}