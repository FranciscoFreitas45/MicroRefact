package com.softserve.edu.Resources.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.Interface.PrivilegeService;
public class PrivilegeServiceImpl implements PrivilegeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Privilege> getAllPrivileges(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllPrivileges"))
;  List<Privilege> aux = restTemplate.getForObject(builder.toUriString(), List<Privilege>.class);

 return aux;
}


}