package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Member getCurrent(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrent"))
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


}