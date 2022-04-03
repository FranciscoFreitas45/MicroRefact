package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Member getCurrent(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCurrent"))
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


}