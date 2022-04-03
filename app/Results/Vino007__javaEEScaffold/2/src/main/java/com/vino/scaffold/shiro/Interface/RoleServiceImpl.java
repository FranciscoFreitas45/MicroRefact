package com.vino.scaffold.shiro.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.vino.scaffold.shiro.Interface.RoleService;
public class RoleServiceImpl implements RoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void connectRoleAndResource(Long roleId,Long resourceIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/connectRoleAndResource"))
    .queryParam("roleId",roleId)
    .queryParam("resourceIds",resourceIds)
;
  restTemplate.put(builder.toUriString(), null);
}


}