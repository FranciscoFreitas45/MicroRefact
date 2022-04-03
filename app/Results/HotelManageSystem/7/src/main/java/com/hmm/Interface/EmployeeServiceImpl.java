package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.EmployeeService;
public class EmployeeServiceImpl implements EmployeeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Employee findByEmpNameAndEmpNo(String empName,String empNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpNameAndEmpNo"))
    .queryParam("empName",empName)
    .queryParam("empNo",empNo)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


public Employee findByEmpName(String empName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpName"))
    .queryParam("empName",empName)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


public Employee findByUserName(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserName"))
    .queryParam("userName",userName)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


}