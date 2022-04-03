package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.TownshipDao;
public class TownshipDaoImpl implements TownshipDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<AddressDto> findTownshipByCountyId(long countyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTownshipByCountyId"))
    .queryParam("countyId",countyId)
;  List<AddressDto> aux = restTemplate.getForObject(builder.toUriString(), List<AddressDto>.class);

 return aux;
}


}