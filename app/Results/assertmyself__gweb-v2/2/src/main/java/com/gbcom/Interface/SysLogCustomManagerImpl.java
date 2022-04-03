package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SysLogCustomManager;
public class SysLogCustomManagerImpl implements SysLogCustomManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public void log(HttpServletRequest request,String logType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/log"))
    .queryParam("request",request)
    .queryParam("logType",logType)
;
  restTemplate.put(builder.toUriString(), null);
}


}