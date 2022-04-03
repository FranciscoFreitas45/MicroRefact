package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.VillageDao;
public class VillageDaoImpl implements VillageDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<AddressDto> findVillageByTownshipId(long towhshipId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findVillageByTownshipId"))
    .queryParam("towhshipId",towhshipId)
;  List<AddressDto> aux = restTemplate.getForObject(builder.toUriString(), List<AddressDto>.class);

 return aux;
}


}