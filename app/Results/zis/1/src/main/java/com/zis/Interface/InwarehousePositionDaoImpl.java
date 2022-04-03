package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.InwarehousePositionDao;
public class InwarehousePositionDaoImpl implements InwarehousePositionDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<InwarehousePosition> findAvailablePosition(Integer inwarehouseId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAvailablePosition"))
    .queryParam("inwarehouseId",inwarehouseId)
;  List<InwarehousePosition> aux = restTemplate.getForObject(builder.toUriString(), List<InwarehousePosition>.class);

 return aux;
}


}