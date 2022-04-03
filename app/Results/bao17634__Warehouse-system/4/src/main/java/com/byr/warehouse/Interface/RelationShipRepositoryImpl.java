package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.RelationShipRepository;
public class RelationShipRepositoryImpl implements RelationShipRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<RelationShip> findRelationShipsBysupplyName(String supplyName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRelationShipsBysupplyName"))
    .queryParam("supplyName",supplyName)
;  List<RelationShip> aux = restTemplate.getForObject(builder.toUriString(), List<RelationShip>.class);

 return aux;
}


}