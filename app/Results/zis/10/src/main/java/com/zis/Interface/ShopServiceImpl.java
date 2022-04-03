package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.ShopService;
public class ShopServiceImpl implements ShopService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public ShopInfo findShopById(Integer shopId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findShopById"))
    .queryParam("shopId",shopId)
;  ShopInfo aux = restTemplate.getForObject(builder.toUriString(), ShopInfo.class);

 return aux;
}


}