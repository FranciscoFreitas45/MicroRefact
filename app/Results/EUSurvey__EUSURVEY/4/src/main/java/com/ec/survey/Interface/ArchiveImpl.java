package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.Archive;
public class ArchiveImpl implements Archive{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void setFinished(boolean finished){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFinished"))
    .queryParam("finished",finished)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setError(String error){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setError"))
    .queryParam("error",error)
;
  restTemplate.put(builder.toUriString(), null);
}


}