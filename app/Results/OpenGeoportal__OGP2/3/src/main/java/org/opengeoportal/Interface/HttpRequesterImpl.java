package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.HttpRequester;
public class HttpRequesterImpl implements HttpRequester{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public InputStream sendRequest(String serviceURL,String requestString,String requestMethod,String contentType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendRequest"))
    .queryParam("serviceURL",serviceURL)
    .queryParam("requestString",requestString)
    .queryParam("requestMethod",requestMethod)
    .queryParam("contentType",contentType)
;  InputStream aux = restTemplate.getForObject(builder.toUriString(), InputStream.class);

 return aux;
}


public String getContentType(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getContentType"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}