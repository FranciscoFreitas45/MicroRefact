package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.AccountRoleDao;
public class AccountRoleDaoImpl implements AccountRoleDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<TeAccountRole> findByAcctNameAndRoleLabel(String account,String roleLabel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAcctNameAndRoleLabel"))
    .queryParam("account",account)
    .queryParam("roleLabel",roleLabel)
;  List<TeAccountRole> aux = restTemplate.getForObject(builder.toUriString(), List<TeAccountRole>.class);

 return aux;
}


public void delByAcctNameAndRoleLabel(String account,String roleLabel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delByAcctNameAndRoleLabel"))
    .queryParam("account",account)
    .queryParam("roleLabel",roleLabel)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}