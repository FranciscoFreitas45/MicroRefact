package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeePermissionService;
public class EmployeePermissionServiceImpl implements EmployeePermissionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public RolePermissionDTO getEmployeePermission(Long employeeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmployeePermission"))
    .queryParam("employeeId",employeeId)
;  RolePermissionDTO aux = restTemplate.getForObject(builder.toUriString(), RolePermissionDTO.class);

 return aux;
}


}