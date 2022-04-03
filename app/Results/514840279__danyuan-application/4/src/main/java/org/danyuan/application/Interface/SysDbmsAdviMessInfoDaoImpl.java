package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysDbmsAdviMessInfoDao;
public class SysDbmsAdviMessInfoDaoImpl implements SysDbmsAdviMessInfoDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Object deleteAllInBatch(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllInBatch"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}