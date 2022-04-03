package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.MailService;
public class MailServiceImpl implements MailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void sendFindPasswordMail(String toMail,String username,SafeKey safeKey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendFindPasswordMail"))
    .queryParam("toMail",toMail)
    .queryParam("username",username)
    .queryParam("safeKey",safeKey)
;
  restTemplate.put(builder.toUriString(), null);
}


}