package com.gbcom.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gbcom.Interface.SysUserManager;
public class SysUserManagerImpl implements SysUserManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Set<String> getUserPrivilegeCodesCache(Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserPrivilegeCodesCache"))
    .queryParam("userId",userId)
;  Set<String> aux = restTemplate.getForObject(builder.toUriString(), Set<String>.class);

 return aux;
}


}