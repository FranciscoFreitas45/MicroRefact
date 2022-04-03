package com.weflors.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.weflors.Interface.ProductStatusServiceImpl;
public class ProductStatusServiceImplImpl implements ProductStatusServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public ProductStatusEntity saveProductStatus(ProductStatusEntity productStatusEntity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveProductStatus"))
    .queryParam("productStatusEntity",productStatusEntity)
;  ProductStatusEntity aux = restTemplate.getForObject(builder.toUriString(), ProductStatusEntity.class);

 return aux;
}


}