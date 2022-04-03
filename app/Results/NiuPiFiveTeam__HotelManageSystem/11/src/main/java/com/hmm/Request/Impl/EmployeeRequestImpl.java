package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setEmployee(Employee employee,Integer emp_idWJ6N){
 restTemplate.put("http://16/RoomOrder/{id}/Employee/setEmployee",employee,emp_idWJ6N);
 return ;
}


public Employee getEmployee(Integer emp_idWJ6N){
 Employee aux = restTemplate.getForObject("http://16/RoomOrder/{id}/Employee/getEmployee",Employee.class,emp_idWJ6N);
return aux;
}


}