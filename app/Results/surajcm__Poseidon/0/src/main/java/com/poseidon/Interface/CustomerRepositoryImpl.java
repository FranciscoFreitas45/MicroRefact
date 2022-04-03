package com.poseidon.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.poseidon.Interface.CustomerRepository;
public class CustomerRepositoryImpl implements CustomerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Customer> findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  List<Customer> aux = restTemplate.getForObject(builder.toUriString(), List<Customer>.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}