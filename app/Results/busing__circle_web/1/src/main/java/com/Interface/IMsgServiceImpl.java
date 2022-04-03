package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IMsgService;
public class IMsgServiceImpl implements IMsgService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Map<String,Object>> getUserMessage(Page page,int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserMessage"))
    .queryParam("page",page)
    .queryParam("id",id)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}