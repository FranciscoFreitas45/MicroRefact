package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IRoleService;
public class IRoleServiceImpl implements IRoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<String> queryRoleMenusById(Integer roleId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryRoleMenusById"))
    .queryParam("roleId",roleId)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}