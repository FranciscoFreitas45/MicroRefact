package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.CouponCodeService;
public class CouponCodeServiceImpl implements CouponCodeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public CouponCode findByCode(String code){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCode"))
    .queryParam("code",code)
;  CouponCode aux = restTemplate.getForObject(builder.toUriString(), CouponCode.class);

 return aux;
}


}