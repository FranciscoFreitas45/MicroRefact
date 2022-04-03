package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeRoleRepository;
public class EmployeeRoleRepositoryImpl implements EmployeeRoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<EmployeeRole> findAllByEmployeeId(Long employeeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByEmployeeId"))
    .queryParam("employeeId",employeeId)
;  List<EmployeeRole> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeRole>.class);

 return aux;
}


}