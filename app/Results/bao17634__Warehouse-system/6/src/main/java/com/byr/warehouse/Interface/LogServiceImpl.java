package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.LogService;
public class LogServiceImpl implements LogService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void saveSysLog(String logMessage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveSysLog"))
    .queryParam("logMessage",logMessage)
;
  restTemplate.put(builder.toUriString(), null);
}


}