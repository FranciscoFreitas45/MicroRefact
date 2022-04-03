package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.EmployeeDao;
public class EmployeeDaoImpl implements EmployeeDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object existsById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object deleteById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findAllById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object deleteAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Employee findByEmpNo(String empNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpNo"))
    .queryParam("empNo",empNo)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


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


public void updatePassword(String password,String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updatePassword"))
    .queryParam("password",password)
    .queryParam("userName",userName)
;
  restTemplate.put(builder.toUriString(), null);
}


public Employee findByUserName(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserName"))
    .queryParam("userName",userName)
;  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class);

 return aux;
}


}