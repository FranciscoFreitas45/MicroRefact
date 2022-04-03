package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.CouponCodeService;
public class CouponCodeServiceImpl implements CouponCodeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Long count(Coupon coupon,Member member,Boolean hasBegun,Boolean hasExpired,Boolean isUsed){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("coupon",coupon)
    .queryParam("member",member)
    .queryParam("hasBegun",hasBegun)
    .queryParam("hasExpired",hasExpired)
    .queryParam("isUsed",isUsed)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


}