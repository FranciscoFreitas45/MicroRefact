package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.CouponCodeDao;
public class CouponCodeDaoImpl implements CouponCodeDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object lock(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/lock"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object merge(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/merge"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<CouponCode> build(Coupon coupon,Member member,Integer count){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/build"))
    .queryParam("coupon",coupon)
    .queryParam("member",member)
    .queryParam("count",count)
;  List<CouponCode> aux = restTemplate.getForObject(builder.toUriString(), List<CouponCode>.class);

 return aux;
}


}