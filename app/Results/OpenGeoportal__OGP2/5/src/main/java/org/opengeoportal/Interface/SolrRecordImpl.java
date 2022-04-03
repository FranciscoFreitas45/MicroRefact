package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.SolrRecord;
public class SolrRecordImpl implements SolrRecord{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public String getLayerId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLayerId"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getWorkspaceName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWorkspaceName"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getName"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getLocation(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLocation"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}