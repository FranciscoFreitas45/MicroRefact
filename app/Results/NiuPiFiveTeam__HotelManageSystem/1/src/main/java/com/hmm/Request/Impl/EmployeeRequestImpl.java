package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setEmploy(Employee employ,Integer emp_idR9B4){
 restTemplate.put("http://3/Bcard/{id}/Employee/setEmploy",employ,emp_idR9B4);
 return ;
}


public Employee getEmploy(Integer emp_idR9B4){
 Employee aux = restTemplate.getForObject("http://3/Bcard/{id}/Employee/getEmploy",Employee.class,emp_idR9B4);
return aux;
}


}