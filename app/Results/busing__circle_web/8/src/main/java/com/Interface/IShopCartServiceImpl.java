package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IShopCartService;
public class IShopCartServiceImpl implements IShopCartService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Map<String,List<Map<String,Object>>> queryCalShopCartList(int userId,String calCartId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCalShopCartList"))
    .queryParam("userId",userId)
    .queryParam("calCartId",calCartId)
;  Map<String,List<Map<String,Object>>> aux = restTemplate.getForObject(builder.toUriString(), Map<String,List<Map<String,Object>>>.class);

 return aux;
}


public double calTotal(String[] cartId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/calTotal"))
    .queryParam("cartId",cartId)
;  double aux = restTemplate.getForObject(builder.toUriString(), double.class);

 return aux;
}


}