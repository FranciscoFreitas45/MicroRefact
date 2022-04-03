package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IAdminService;
public class IAdminServiceImpl implements IAdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Map<String,Object>> queryAdminNameToCache(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAdminNameToCache"))
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}