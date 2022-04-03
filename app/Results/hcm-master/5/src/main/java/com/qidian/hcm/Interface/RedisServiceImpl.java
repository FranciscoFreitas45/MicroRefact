package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.RedisService;
public class RedisServiceImpl implements RedisService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void set(String key,Object value,Long expireTime){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/set"))
    .queryParam("key",key)
    .queryParam("value",value)
    .queryParam("expireTime",expireTime)
;
  restTemplate.put(builder.toUriString(), null);
}


}