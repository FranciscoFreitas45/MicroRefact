package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.EmployeeDao;
public class EmployeeDaoImpl implements EmployeeDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public Employee findByUserName(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserName"))
    .queryParam("userName",userName)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


}