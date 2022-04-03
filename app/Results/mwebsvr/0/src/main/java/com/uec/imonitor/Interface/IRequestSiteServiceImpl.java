package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IRequestSiteService;
public class IRequestSiteServiceImpl implements IRequestSiteService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<RequestSiteEntity> findByRequestId(Integer requestId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRequestId"))
    .queryParam("requestId",requestId)
;  List<RequestSiteEntity> aux = restTemplate.getForObject(builder.toUriString(), List<RequestSiteEntity>.class);

 return aux;
}


}