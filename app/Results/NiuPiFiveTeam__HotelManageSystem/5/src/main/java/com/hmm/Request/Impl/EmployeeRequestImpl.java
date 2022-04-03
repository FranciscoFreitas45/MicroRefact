package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setEmployee(Employee employee,Integer emp_idIGL6){
 restTemplate.put("http://16/InStorage/{id}/Employee/setEmployee",employee,emp_idIGL6);
 return ;
}


public Employee getEmployee(Integer emp_idIGL6){
 Employee aux = restTemplate.getForObject("http://16/InStorage/{id}/Employee/getEmployee",Employee.class,emp_idIGL6);
return aux;
}


}