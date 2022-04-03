package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.LogService;
public class LogServiceImpl implements LogService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void saveOpLog(String username,String operation,String result,String detail){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveOpLog"))
    .queryParam("username",username)
    .queryParam("operation",operation)
    .queryParam("result",result)
    .queryParam("detail",detail)
;
  restTemplate.put(builder.toUriString(), null);
}


}