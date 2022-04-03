package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.DatabaseService;
import com.sda.inTeams.DTO.*;
public class DatabaseServiceImpl implements DatabaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public DatabaseInfo getDatabaseInfo(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDatabaseInfo"))
;  DatabaseInfo aux = restTemplate.getForObject(builder.toUriString(), DatabaseInfo.class);

 return aux;
}


}