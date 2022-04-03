package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.RoleService;
public class RoleServiceImpl implements RoleService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://17";


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object update(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int updateStatus(Role role){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateStatus"))
    .queryParam("role",role)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Role> queryAll(String rolestatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAll"))
    .queryParam("rolestatus",rolestatus)
;  List<Role> aux = restTemplate.getForObject(builder.toUriString(), List<Role>.class);

 return aux;
}


public int count(String roleStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("roleStatus",roleStatus)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Role> queryByPager(String roleStatus,Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByPager"))
    .queryParam("roleStatus",roleStatus)
    .queryParam("pager",pager)
;  List<Role> aux = restTemplate.getForObject(builder.toUriString(), List<Role>.class);

 return aux;
}


}