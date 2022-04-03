package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.PermissionsService;
public class PermissionsServiceImpl implements PermissionsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void create(Permissions permissions){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("permissions",permissions)
;
  restTemplate.put(builder.toUriString(), null);
}


}