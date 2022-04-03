package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductService;
public class ProductServiceImpl implements ProductService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Long count(Member favoriteMember,Boolean isMarketable,Boolean isList,Boolean isTop,Boolean isGift,Boolean isOutOfStock,Boolean isStockAlert){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("favoriteMember",favoriteMember)
    .queryParam("isMarketable",isMarketable)
    .queryParam("isList",isList)
    .queryParam("isTop",isTop)
    .queryParam("isGift",isGift)
    .queryParam("isOutOfStock",isOutOfStock)
    .queryParam("isStockAlert",isStockAlert)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


}