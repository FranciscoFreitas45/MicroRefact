package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IRoleService;
public class IRoleServiceImpl implements IRoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<WcRole> queryRoleForAdminAdd(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryRoleForAdminAdd"))
;  List<WcRole> aux = restTemplate.getForObject(builder.toUriString(), List<WcRole>.class);

 return aux;
}


public List<WcRole> queryRoleForAdminUpd0(Integer adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryRoleForAdminUpd0"))
    .queryParam("adminId",adminId)
;  List<WcRole> aux = restTemplate.getForObject(builder.toUriString(), List<WcRole>.class);

 return aux;
}


public List<WcRole> queryRoleForAdminUpd1(Integer adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryRoleForAdminUpd1"))
    .queryParam("adminId",adminId)
;  List<WcRole> aux = restTemplate.getForObject(builder.toUriString(), List<WcRole>.class);

 return aux;
}


}