package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.OperateLogService;
public class OperateLogServiceImpl implements OperateLogService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void addLog(String beforeConf,String afterConf,String adminName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addLog"))
    .queryParam("beforeConf",beforeConf)
    .queryParam("afterConf",afterConf)
    .queryParam("adminName",adminName)
;
  restTemplate.put(builder.toUriString(), null);
}


}