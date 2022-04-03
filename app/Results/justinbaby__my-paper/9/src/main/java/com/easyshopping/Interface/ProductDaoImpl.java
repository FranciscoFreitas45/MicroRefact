package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.ProductDao;
public class ProductDaoImpl implements ProductDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Product> findList(Goods goods,Set<Product> excludes){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findList"))
    .queryParam("goods",goods)
    .queryParam("excludes",excludes)
;  List<Product> aux = restTemplate.getForObject(builder.toUriString(), List<Product>.class);

 return aux;
}


public Object clear(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clear"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object flush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/flush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


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