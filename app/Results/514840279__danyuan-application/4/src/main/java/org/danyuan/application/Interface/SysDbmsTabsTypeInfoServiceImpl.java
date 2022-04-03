package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysDbmsTabsTypeInfoService;
public class SysDbmsTabsTypeInfoServiceImpl implements SysDbmsTabsTypeInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<SysDbmsTabsTypeInfo> findAllTypeByUser(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllTypeByUser"))
    .queryParam("username",username)
;  List<SysDbmsTabsTypeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<SysDbmsTabsTypeInfo>.class);

 return aux;
}


public List<SysDbmsTabsTypeInfo> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<SysDbmsTabsTypeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<SysDbmsTabsTypeInfo>.class);

 return aux;
}


}