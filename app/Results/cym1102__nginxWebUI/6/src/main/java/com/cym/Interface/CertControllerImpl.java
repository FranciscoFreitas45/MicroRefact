package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.CertController;
public class CertControllerImpl implements CertController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public JsonResult apply(String id,String type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/apply"))
    .queryParam("id",id)
    .queryParam("type",type)
;  JsonResult aux = restTemplate.getForObject(builder.toUriString(), JsonResult.class);

 return aux;
}


}