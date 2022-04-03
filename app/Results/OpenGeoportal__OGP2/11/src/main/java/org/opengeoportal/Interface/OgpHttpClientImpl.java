package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.OgpHttpClient;
public class OgpHttpClientImpl implements OgpHttpClient{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public CloseableHttpClient getCloseableHttpClient(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCloseableHttpClient"))
;  CloseableHttpClient aux = restTemplate.getForObject(builder.toUriString(), CloseableHttpClient.class);

 return aux;
}


}