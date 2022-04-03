package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.VersionConfig;
public class VersionConfigImpl implements VersionConfig{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void getNewVersion(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNewVersion"))
;
  restTemplate.put(builder.toUriString(), null);
}


}