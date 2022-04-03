package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysDbmsTabsMergeInfoDao;
public class SysDbmsTabsMergeInfoDaoImpl implements SysDbmsTabsMergeInfoDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}