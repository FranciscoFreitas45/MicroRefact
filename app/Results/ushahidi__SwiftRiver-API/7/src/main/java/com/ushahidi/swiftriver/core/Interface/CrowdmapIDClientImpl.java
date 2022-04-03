package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.CrowdmapIDClient;
public class CrowdmapIDClientImpl implements CrowdmapIDClient{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public boolean isRegistered(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isRegistered"))
    .queryParam("email",email)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public String register(String email,String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/register"))
    .queryParam("email",email)
    .queryParam("password",password)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}