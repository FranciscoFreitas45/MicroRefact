package com.weflors.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.weflors.Interface.ProductStatusServiceImpl;
public class ProductStatusServiceImplImpl implements ProductStatusServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public ProductStatusEntity findProductStatusEntity(Integer productId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findProductStatusEntity"))
    .queryParam("productId",productId)
;  ProductStatusEntity aux = restTemplate.getForObject(builder.toUriString(), ProductStatusEntity.class);

 return aux;
}


public void updateQuantityWriteoffAndWarehouse(Integer productId,Integer quantityWriteoff){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateQuantityWriteoffAndWarehouse"))
    .queryParam("productId",productId)
    .queryParam("quantityWriteoff",quantityWriteoff)
;
  restTemplate.put(builder.toUriString(), null);
}


}