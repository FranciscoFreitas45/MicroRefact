package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysDbmsUserIndexInfoService;
public class SysDbmsUserIndexInfoServiceImpl implements SysDbmsUserIndexInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<SysDbmsUserIndexInfo> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<SysDbmsUserIndexInfo> aux = restTemplate.getForObject(builder.toUriString(), List<SysDbmsUserIndexInfo>.class);

 return aux;
}


}