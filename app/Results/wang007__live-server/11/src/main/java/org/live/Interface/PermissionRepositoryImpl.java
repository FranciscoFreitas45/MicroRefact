package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.PermissionRepository;
public class PermissionRepositoryImpl implements PermissionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<Permission> findPermissionsByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPermissionsByUsername"))
    .queryParam("username",username)
;  List<Permission> aux = restTemplate.getForObject(builder.toUriString(), List<Permission>.class);

 return aux;
}


}