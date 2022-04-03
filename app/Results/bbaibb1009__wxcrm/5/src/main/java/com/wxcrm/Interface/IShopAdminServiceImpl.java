package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IShopAdminService;
public class IShopAdminServiceImpl implements IShopAdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public WcShopAdmin getShopAdminById(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getShopAdminById"))
    .queryParam("id",id)
;  WcShopAdmin aux = restTemplate.getForObject(builder.toUriString(), WcShopAdmin.class);

 return aux;
}


}