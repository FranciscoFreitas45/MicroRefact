package com.cg.sprint.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.sprint.Interface.CityDao;
public class CityDaoImpl implements CityDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<City> getCityNames(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCityNames"))
;  List<City> aux = restTemplate.getForObject(builder.toUriString(), List<City>.class);

 return aux;
}


}