package com.cg.sprint.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.sprint.Interface.ShowsDao;
public class ShowsDaoImpl implements ShowsDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Shows> getShows(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getShows"))
;  List<Shows> aux = restTemplate.getForObject(builder.toUriString(), List<Shows>.class);

 return aux;
}


}