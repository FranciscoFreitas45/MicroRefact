package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.YouLuNetDetailCapture;
public class YouLuNetDetailCaptureImpl implements YouLuNetDetailCapture{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Integer getBookIdByIsbn(String isbn){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBookIdByIsbn"))
    .queryParam("isbn",isbn)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}