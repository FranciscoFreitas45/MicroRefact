package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SqlQueryService;
public class SqlQueryServiceImpl implements SqlQueryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void setParameters(Query query,Map<String,Object> parameters){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParameters"))
    .queryParam("query",query)
    .queryParam("parameters",parameters)
;
  restTemplate.put(builder.toUriString(), null);
}


}