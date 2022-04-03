package com.example.demo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Interface.ProductService;
public class ProductServiceImpl implements ProductService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Product findByIdproduct(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdproduct"))
    .queryParam("id",id)
;  Product aux = restTemplate.getForObject(builder.toUriString(), Product.class);

 return aux;
}


public Product getOne(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOne"))
    .queryParam("id",id)
;  Product aux = restTemplate.getForObject(builder.toUriString(), Product.class);

 return aux;
}


}