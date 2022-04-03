package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.SearchService;
public class SearchServiceImpl implements SearchService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Page<Product> search(String keyword,BigDecimal startPrice,BigDecimal endPrice,OrderType orderType,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/search"))
    .queryParam("keyword",keyword)
    .queryParam("startPrice",startPrice)
    .queryParam("endPrice",endPrice)
    .queryParam("orderType",orderType)
    .queryParam("pageable",pageable)
;  Page<Product> aux = restTemplate.getForObject(builder.toUriString(), Page<Product>.class);

 return aux;
}


}