package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setEmploy(Employee employ,Integer emp_idPBMQ){
 restTemplate.put("http://16/Travel/{id}/Employee/setEmploy",employ,emp_idPBMQ);
 return ;
}


public Employee getEmploy(Integer emp_idPBMQ){
 Employee aux = restTemplate.getForObject("http://16/Travel/{id}/Employee/getEmploy",Employee.class,emp_idPBMQ);
return aux;
}


}