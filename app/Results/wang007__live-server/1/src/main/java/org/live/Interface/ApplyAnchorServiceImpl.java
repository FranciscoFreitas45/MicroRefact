package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.ApplyAnchorService;
public class ApplyAnchorServiceImpl implements ApplyAnchorService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public boolean judgeUserApplyCount(String userId,int systemMaxCount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/judgeUserApplyCount"))
    .queryParam("userId",userId)
    .queryParam("systemMaxCount",systemMaxCount)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Date getLastApplyAnchorDate(String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLastApplyAnchorDate"))
    .queryParam("userId",userId)
;  Date aux = restTemplate.getForObject(builder.toUriString(), Date.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}