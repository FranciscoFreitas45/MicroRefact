package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.ParamService;
public class ParamServiceImpl implements ParamService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Param> getListByTypeId(String id,String type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListByTypeId"))
    .queryParam("id",id)
    .queryParam("type",type)
;  List<Param> aux = restTemplate.getForObject(builder.toUriString(), List<Param>.class);

 return aux;
}


}