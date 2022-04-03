package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.EmployeeRepository;
public class EmployeeRepositoryImpl implements EmployeeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Optional<Employee> findByEmployeeNo(String employeeNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmployeeNo"))
    .queryParam("employeeNo",employeeNo)
;  Optional<Employee> aux = restTemplate.getForObject(builder.toUriString(), Optional<Employee>.class);

 return aux;
}


}