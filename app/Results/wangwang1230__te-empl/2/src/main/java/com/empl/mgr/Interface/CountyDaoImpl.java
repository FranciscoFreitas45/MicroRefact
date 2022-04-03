package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.CountyDao;
public class CountyDaoImpl implements CountyDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<AddressDto> findCountyByCityId(long cityId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCountyByCityId"))
    .queryParam("cityId",cityId)
;  List<AddressDto> aux = restTemplate.getForObject(builder.toUriString(), List<AddressDto>.class);

 return aux;
}


}