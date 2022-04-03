package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.ShoppingCartService;
public class ShoppingCartServiceImpl implements ShoppingCartService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<ShoppingCartDetail> findAllCart(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllCart"))
;  List<ShoppingCartDetail> aux = restTemplate.getForObject(builder.toUriString(), List<ShoppingCartDetail>.class);

 return aux;
}


}