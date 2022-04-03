package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.Permission;
import org.live.Request.PermissionRequest;
public class PermissionRequestImpl implements PermissionRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Permission getPermission(String idIJY1){
 Permission aux = restTemplate.getForObject("http://10/PageElement/{id}/Permission/getPermission",Permission.class,idIJY1);
return aux;
}


public void setPermission(Permission permission,String idIJY1){
 restTemplate.put("http://10/PageElement/{id}/Permission/setPermission",permission,idIJY1);
 return ;
}


}