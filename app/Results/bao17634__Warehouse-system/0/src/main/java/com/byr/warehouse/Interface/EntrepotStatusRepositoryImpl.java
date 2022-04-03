package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
public class EntrepotStatusRepositoryImpl implements EntrepotStatusRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<EntrepotStatus> findByEnterCodeAndMaterialCode(String enterCode,String materialCode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEnterCodeAndMaterialCode"))
    .queryParam("enterCode",enterCode)
    .queryParam("materialCode",materialCode)
;  List<EntrepotStatus> aux = restTemplate.getForObject(builder.toUriString(), List<EntrepotStatus>.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}