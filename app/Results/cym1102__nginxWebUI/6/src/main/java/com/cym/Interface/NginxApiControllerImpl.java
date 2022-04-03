package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.NginxApiController;
public class NginxApiControllerImpl implements NginxApiController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public JsonResult<List<String>> getNginxStartCmd(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNginxStartCmd"))
;  JsonResult<List<String>> aux = restTemplate.getForObject(builder.toUriString(), JsonResult<List<String>>.class);

 return aux;
}


public JsonResult<List<String>> getNginxStopCmd(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNginxStopCmd"))
;  JsonResult<List<String>> aux = restTemplate.getForObject(builder.toUriString(), JsonResult<List<String>>.class);

 return aux;
}


}