package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IMenuService;
public class IMenuServiceImpl implements IMenuService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<Map<String,Object>> queryMenuToCache(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryMenuToCache"))
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public List<Map<String,Object>> queryShopMenuToCache(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryShopMenuToCache"))
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}