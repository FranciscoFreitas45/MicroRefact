package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IReceiveInfoService;
public class IReceiveInfoServiceImpl implements IReceiveInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public boolean isExistsReceiveInfo(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isExistsReceiveInfo"))
    .queryParam("userId",userId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public boolean saveDefaultReceiveInfo(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveDefaultReceiveInfo"))
    .queryParam("user",user)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public List<Map<String,Object>> queryReceiveInfo(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryReceiveInfo"))
    .queryParam("userId",userId)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


public Map<String,Object> queryPayAndShipInfo(int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryPayAndShipInfo"))
    .queryParam("userId",userId)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}