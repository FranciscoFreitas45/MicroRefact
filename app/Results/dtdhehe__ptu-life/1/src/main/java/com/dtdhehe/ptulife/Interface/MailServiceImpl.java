package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.MailService;
public class MailServiceImpl implements MailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void sendHtmlMail(String to,String subject,String content){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendHtmlMail"))
    .queryParam("to",to)
    .queryParam("subject",subject)
    .queryParam("content",content)
;
  restTemplate.put(builder.toUriString(), null);
}


}