package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.ReportService;
public class ReportServiceImpl implements ReportService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Map<String,Object> getPersonCreditReport(String login_name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPersonCreditReport"))
    .queryParam("login_name",login_name)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


public boolean parseAndSave(String content,String loginName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parseAndSave"))
    .queryParam("content",content)
    .queryParam("loginName",loginName)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}