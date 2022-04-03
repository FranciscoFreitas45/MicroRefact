package com.cg.sprint.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.sprint.Interface.MoviesDao;
public class MoviesDaoImpl implements MoviesDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Movies> movieNames(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/movieNames"))
    .queryParam("name",name)
;  List<Movies> aux = restTemplate.getForObject(builder.toUriString(), List<Movies>.class);

 return aux;
}


}