package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.RolePermissionService;
public class RolePermissionServiceImpl implements RolePermissionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public List<Permission> queryPermissions(String roleId,String roleStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryPermissions"))
    .queryParam("roleId",roleId)
    .queryParam("roleStatus",roleStatus)
;  List<Permission> aux = restTemplate.getForObject(builder.toUriString(), List<Permission>.class);

 return aux;
}


public int insertList(String roleId,List<String> permissionIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertList"))
    .queryParam("roleId",roleId)
    .queryParam("permissionIds",permissionIds)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int removePermission(String roleId,String permissionId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/removePermission"))
    .queryParam("roleId",roleId)
    .queryParam("permissionId",permissionId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int addPermission(String roleId,String permissionId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addPermission"))
    .queryParam("roleId",roleId)
    .queryParam("permissionId",permissionId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}