package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Employee getEmploy(Integer emp_idSDYK){
 Employee aux = restTemplate.getForObject("http://16/Work/{id}/Employee/getEmploy",Employee.class,emp_idSDYK);
return aux;
}


public void setEmploy(Employee employ,Integer emp_idSDYK){
 restTemplate.put("http://16/Work/{id}/Employee/setEmploy",employ,emp_idSDYK);
 return ;
}


}