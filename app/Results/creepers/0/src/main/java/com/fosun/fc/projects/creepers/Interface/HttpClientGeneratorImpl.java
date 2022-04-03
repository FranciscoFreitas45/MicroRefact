package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.HttpClientGenerator;
public class HttpClientGeneratorImpl implements HttpClientGenerator{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public CloseableHttpClient getClient(Site site){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClient"))
    .queryParam("site",site)
;  CloseableHttpClient aux = restTemplate.getForObject(builder.toUriString(), CloseableHttpClient.class);

 return aux;
}


}