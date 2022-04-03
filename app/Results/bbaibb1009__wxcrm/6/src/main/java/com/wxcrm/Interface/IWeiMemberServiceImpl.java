package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IWeiMemberService;
public class IWeiMemberServiceImpl implements IWeiMemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Page queryWeiMember(LzWeiMember member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryWeiMember"))
    .queryParam("member",member)
;  Page aux = restTemplate.getForObject(builder.toUriString(), Page.class);

 return aux;
}


public void delWeiMember(String[] wmbIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delWeiMember"))
    .queryParam("wmbIds",wmbIds)
;
  restTemplate.put(builder.toUriString(), null);
}


}