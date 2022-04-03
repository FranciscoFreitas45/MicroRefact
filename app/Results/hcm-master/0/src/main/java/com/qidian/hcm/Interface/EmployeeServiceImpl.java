package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeService;
public class EmployeeServiceImpl implements EmployeeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Employee findByUserId(Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserId"))
    .queryParam("userId",userId)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


}