package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.ConfController;
public class ConfControllerImpl implements ConfController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public JsonResult reload(String nginxPath,String nginxExe,String nginxDir){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reload"))
    .queryParam("nginxPath",nginxPath)
    .queryParam("nginxExe",nginxExe)
    .queryParam("nginxDir",nginxDir)
;  JsonResult aux = restTemplate.getForObject(builder.toUriString(), JsonResult.class);

 return aux;
}


}