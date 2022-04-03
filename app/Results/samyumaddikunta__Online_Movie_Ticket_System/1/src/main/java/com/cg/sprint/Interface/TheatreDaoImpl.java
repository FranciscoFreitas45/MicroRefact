package com.cg.sprint.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.sprint.Interface.TheatreDao;
public class TheatreDaoImpl implements TheatreDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Theatre> theatreNames(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/theatreNames"))
    .queryParam("name",name)
;  List<Theatre> aux = restTemplate.getForObject(builder.toUriString(), List<Theatre>.class);

 return aux;
}


}