package com.yalcin.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.Interface.SellerBeginService;
public class SellerBeginServiceImpl implements SellerBeginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void save(SellerBeginForm sellerBeginForm){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("sellerBeginForm",sellerBeginForm)
;
  restTemplate.put(builder.toUriString(), null);
}


}