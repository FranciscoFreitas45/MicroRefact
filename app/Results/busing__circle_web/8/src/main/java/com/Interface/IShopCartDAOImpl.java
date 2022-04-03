package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IShopCartDAO;
public class IShopCartDAOImpl implements IShopCartDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public boolean deleteShopCart(String cartId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteShopCart"))
    .queryParam("cartId",cartId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}