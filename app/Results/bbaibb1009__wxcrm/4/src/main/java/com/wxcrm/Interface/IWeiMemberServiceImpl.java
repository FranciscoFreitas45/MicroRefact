package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IWeiMemberService;
public class IWeiMemberServiceImpl implements IWeiMemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public boolean checkOpenIdExsit(String openId,Integer wecId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkOpenIdExsit"))
    .queryParam("openId",openId)
    .queryParam("wecId",wecId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void addWeiMember(LzWeiMember member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addWeiMember"))
    .queryParam("member",member)
;
  restTemplate.put(builder.toUriString(), null);
}


}