package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.SimpleMailSender;
public class SimpleMailSenderImpl implements SimpleMailSender{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void send(String[] recipients,String subject,Object content,String filePath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/send"))
    .queryParam("recipients",recipients)
    .queryParam("subject",subject)
    .queryParam("content",content)
    .queryParam("filePath",filePath)
;
  restTemplate.put(builder.toUriString(), null);
}


}