package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.TemplateService;
public class TemplateServiceImpl implements TemplateService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Param> getParamList(String templateId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getParamList"))
    .queryParam("templateId",templateId)
;  List<Param> aux = restTemplate.getForObject(builder.toUriString(), List<Param>.class);

 return aux;
}


}