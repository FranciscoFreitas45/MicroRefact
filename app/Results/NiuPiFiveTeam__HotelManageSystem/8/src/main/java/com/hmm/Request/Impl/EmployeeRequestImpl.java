package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Employee getEmploy(Integer emp_idOBPL){
 Employee aux = restTemplate.getForObject("http://16/SchedulEvent/{id}/Employee/getEmploy",Employee.class,emp_idOBPL);
return aux;
}


public void setEmploy(Employee employ,Integer emp_idOBPL){
 restTemplate.put("http://16/SchedulEvent/{id}/Employee/setEmploy",employ,emp_idOBPL);
 return ;
}


}