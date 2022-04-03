package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SysLogManager;
public class SysLogManagerImpl implements SysLogManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void deleteLog(Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteLog"))
    .queryParam("userId",userId)
;
  restTemplate.put(builder.toUriString(), null);
}


public List<SysLog> getTodayLoginCount(String time){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTodayLoginCount"))
    .queryParam("time",time)
;  List<SysLog> aux = restTemplate.getForObject(builder.toUriString(), List<SysLog>.class);

 return aux;
}


}