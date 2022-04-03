package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.BasicService;
public class BasicServiceImpl implements BasicService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Basic findByReportId(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByReportId"))
    .queryParam("id",id)
;  Basic aux = restTemplate.getForObject(builder.toUriString(), Basic.class);

 return aux;
}


public Basic save(Basic basic){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("basic",basic)
;  Basic aux = restTemplate.getForObject(builder.toUriString(), Basic.class);

 return aux;
}


}