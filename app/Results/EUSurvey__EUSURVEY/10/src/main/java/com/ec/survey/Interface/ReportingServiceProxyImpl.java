package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.ReportingServiceProxy;
public class ReportingServiceProxyImpl implements ReportingServiceProxy{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public int getCount(Survey survey,String quid,String auid,boolean noPrefixSearch,boolean noPostfixSearch,String where,Map<String,Object> values){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCount"))
    .queryParam("survey",survey)
    .queryParam("quid",quid)
    .queryParam("auid",auid)
    .queryParam("noPrefixSearch",noPrefixSearch)
    .queryParam("noPostfixSearch",noPostfixSearch)
    .queryParam("where",where)
    .queryParam("values",values)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public boolean OLAPTableExists(String uid,boolean draft){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/OLAPTableExists"))
    .queryParam("uid",uid)
    .queryParam("draft",draft)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}