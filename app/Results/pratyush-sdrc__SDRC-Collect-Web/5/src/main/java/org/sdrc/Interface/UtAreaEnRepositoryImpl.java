package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.UtAreaEnRepository;
public class UtAreaEnRepositoryImpl implements UtAreaEnRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<UtAreaEn> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<UtAreaEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtAreaEn>.class);

 return aux;
}


public List<UtAreaEn> findByAreaShortName(String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAreaShortName"))
    .queryParam("string",string)
;  List<UtAreaEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtAreaEn>.class);

 return aux;
}


}