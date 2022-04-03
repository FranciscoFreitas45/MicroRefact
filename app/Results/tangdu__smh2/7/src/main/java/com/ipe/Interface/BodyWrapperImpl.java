package com.ipe.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ipe.Interface.BodyWrapper;
public class BodyWrapperImpl implements BodyWrapper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void setRows(Object rows){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRows"))
    .queryParam("rows",rows)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setTotal(Long total){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTotal"))
    .queryParam("total",total)
;
  restTemplate.put(builder.toUriString(), null);
}


public void setSuccess(Boolean success){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSuccess"))
    .queryParam("success",success)
;
  restTemplate.put(builder.toUriString(), null);
}


}