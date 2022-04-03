package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.CityDao;
public class CityDaoImpl implements CityDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<AddressDto> findCityByProvinceId(long provinceId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCityByProvinceId"))
    .queryParam("provinceId",provinceId)
;  List<AddressDto> aux = restTemplate.getForObject(builder.toUriString(), List<AddressDto>.class);

 return aux;
}


}