package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.SysVarCache;
public class SysVarCacheImpl implements SysVarCache{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Integer getSystemVar(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSystemVar"))
    .queryParam("key",key)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}