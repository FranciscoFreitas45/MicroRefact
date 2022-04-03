package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setEmployee(Set<Employee> employ,Integer dept_id){
 restTemplate.put("http://16/Department/{id}/Employee/setEmployee",employ,dept_id);
 return ;
}


public Set<Employee> getEmployee(Integer dept_id){
 Set<Employee> aux = restTemplate.getForObject("http://16/Department/{id}/Employee/getEmployee",Set<Employee>.class,dept_id);
return aux;
}


}