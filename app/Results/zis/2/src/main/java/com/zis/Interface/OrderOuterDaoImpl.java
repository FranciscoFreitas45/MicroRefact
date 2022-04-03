package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.OrderOuterDao;
public class OrderOuterDaoImpl implements OrderOuterDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<OrderOuter> findByOrderGroupNumber(String orderGroupNumber){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrderGroupNumber"))
    .queryParam("orderGroupNumber",orderGroupNumber)
;  List<OrderOuter> aux = restTemplate.getForObject(builder.toUriString(), List<OrderOuter>.class);

 return aux;
}


}