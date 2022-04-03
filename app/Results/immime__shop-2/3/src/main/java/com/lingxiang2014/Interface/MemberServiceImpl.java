package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Member find(Member parent,Integer index,MemberRank membeRank){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("parent",parent)
    .queryParam("index",index)
    .queryParam("membeRank",membeRank)
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


}