package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SysMenuManager;
public class SysMenuManagerImpl implements SysMenuManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public String getUserMenuJson(Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserMenuJson"))
    .queryParam("userId",userId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}