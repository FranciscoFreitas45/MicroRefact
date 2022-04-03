package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IMemcachedService;
public class IMemcachedServiceImpl implements IMemcachedService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<Map<String,Object>> getAdminNameAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAdminNameAll"))
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}