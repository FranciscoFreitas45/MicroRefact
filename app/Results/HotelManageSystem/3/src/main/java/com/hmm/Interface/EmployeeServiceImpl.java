package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.EmployeeService;
public class EmployeeServiceImpl implements EmployeeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Employee findByEmpNo(String empNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpNo"))
    .queryParam("empNo",empNo)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


}