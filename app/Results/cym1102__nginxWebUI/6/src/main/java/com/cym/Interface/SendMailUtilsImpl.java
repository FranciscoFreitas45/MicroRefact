package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.SendMailUtils;
public class SendMailUtilsImpl implements SendMailUtils{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void sendMailSmtp(String to,String title,String msg){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendMailSmtp"))
    .queryParam("to",to)
    .queryParam("title",title)
    .queryParam("msg",msg)
;
  restTemplate.put(builder.toUriString(), null);
}


}