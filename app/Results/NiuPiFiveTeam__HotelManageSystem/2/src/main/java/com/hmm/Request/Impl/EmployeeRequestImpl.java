package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setEmploy(Employee employ,Integer emp_idB9PD){
 restTemplate.put("http://3/Leave/{id}/Employee/setEmploy",employ,emp_idB9PD);
 return ;
}


public Employee getEmploy(Integer emp_idB9PD){
 Employee aux = restTemplate.getForObject("http://3/Leave/{id}/Employee/getEmploy",Employee.class,emp_idB9PD);
return aux;
}


}