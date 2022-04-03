package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.ChooseNationalDao;
public class ChooseNationalDaoImpl implements ChooseNationalDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}