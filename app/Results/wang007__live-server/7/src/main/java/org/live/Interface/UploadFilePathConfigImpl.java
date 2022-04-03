package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.UploadFilePathConfig;
public class UploadFilePathConfigImpl implements UploadFilePathConfig{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public String getUploadFileRootPath(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUploadFileRootPath"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getUploadFilePath(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUploadFilePath"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getUploadFilePathPrefix(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUploadFilePathPrefix"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}