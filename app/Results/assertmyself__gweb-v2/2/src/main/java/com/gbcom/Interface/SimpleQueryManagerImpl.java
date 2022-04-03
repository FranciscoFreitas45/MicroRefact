package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SimpleQueryManager;
public class SimpleQueryManagerImpl implements SimpleQueryManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String getStringBySql(String sql){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getStringBySql"))
    .queryParam("sql",sql)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}