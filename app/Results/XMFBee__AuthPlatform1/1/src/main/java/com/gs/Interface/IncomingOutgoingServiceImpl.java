package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.IncomingOutgoingService;
public class IncomingOutgoingServiceImpl implements IncomingOutgoingService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public List<IncomingOutgoing> queryByCompanyIdForInType(String companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByCompanyIdForInType"))
    .queryParam("companyId",companyId)
;  List<IncomingOutgoing> aux = restTemplate.getForObject(builder.toUriString(), List<IncomingOutgoing>.class);

 return aux;
}


public List<IncomingOutgoing> queryByCompanyIdForOutType(String companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByCompanyIdForOutType"))
    .queryParam("companyId",companyId)
;  List<IncomingOutgoing> aux = restTemplate.getForObject(builder.toUriString(), List<IncomingOutgoing>.class);

 return aux;
}


}