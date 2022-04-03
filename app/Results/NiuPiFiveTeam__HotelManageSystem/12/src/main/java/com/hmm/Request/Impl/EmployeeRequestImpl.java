package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setEmployee(Employee employee,Integer emp_idQ9YD){
 restTemplate.put("http://16/SalaryOrder/{id}/Employee/setEmployee",employee,emp_idQ9YD);
 return ;
}


public Employee getEmployee(Integer emp_idQ9YD){
 Employee aux = restTemplate.getForObject("http://16/SalaryOrder/{id}/Employee/getEmployee",Employee.class,emp_idQ9YD);
return aux;
}


}