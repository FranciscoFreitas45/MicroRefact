package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.TimeExeUtils;
public class TimeExeUtilsImpl implements TimeExeUtils{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public String execCMD(String cmd,String[] envs,long timeout){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/execCMD"))
    .queryParam("cmd",cmd)
    .queryParam("envs",envs)
    .queryParam("timeout",timeout)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}