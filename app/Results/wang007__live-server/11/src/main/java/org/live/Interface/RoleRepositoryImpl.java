package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.RoleRepository;
public class RoleRepositoryImpl implements RoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Role> findRolesByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findRolesByUsername"))
    .queryParam("username",username)
;  List<Role> aux = restTemplate.getForObject(builder.toUriString(), List<Role>.class);

 return aux;
}


}