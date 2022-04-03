package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysUserBaseService;
public class SysUserBaseServiceImpl implements SysUserBaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public SysUserBaseInfo save(SysUserBaseInfo info){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("info",info)
;  SysUserBaseInfo aux = restTemplate.getForObject(builder.toUriString(), SysUserBaseInfo.class);

 return aux;
}


}