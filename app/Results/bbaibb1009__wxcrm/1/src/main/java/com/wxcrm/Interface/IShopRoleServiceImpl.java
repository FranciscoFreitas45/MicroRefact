package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IShopRoleService;
public class IShopRoleServiceImpl implements IShopRoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<WcShopRole> queryShopRoleForAdminAdd(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryShopRoleForAdminAdd"))
;  List<WcShopRole> aux = restTemplate.getForObject(builder.toUriString(), List<WcShopRole>.class);

 return aux;
}


public List<WcShopRole> queryShopRoleForAdminUpd0(Integer adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryShopRoleForAdminUpd0"))
    .queryParam("adminId",adminId)
;  List<WcShopRole> aux = restTemplate.getForObject(builder.toUriString(), List<WcShopRole>.class);

 return aux;
}


public List<WcShopRole> queryShopRoleForAdminUpd1(Integer adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryShopRoleForAdminUpd1"))
    .queryParam("adminId",adminId)
;  List<WcShopRole> aux = restTemplate.getForObject(builder.toUriString(), List<WcShopRole>.class);

 return aux;
}


}