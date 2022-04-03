package com.csquard.mregister.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.csquard.mregister.Interface.AsmRepository;
public class AsmRepositoryImpl implements AsmRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Boolean existsBySalesRegionId(Long salesRegionId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsBySalesRegionId"))
    .queryParam("salesRegionId",salesRegionId)
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}