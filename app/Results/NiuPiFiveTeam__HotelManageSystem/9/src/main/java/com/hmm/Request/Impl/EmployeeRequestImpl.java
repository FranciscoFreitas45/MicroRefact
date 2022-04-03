package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setSendWorker(Employee sendWorker,Integer emp_idK9MW){
 restTemplate.put("http://16/DoSend/{id}/Employee/setSendWorker",sendWorker,emp_idK9MW);
 return ;
}


public Employee getSendWorker(Integer emp_idK9MW){
 Employee aux = restTemplate.getForObject("http://16/DoSend/{id}/Employee/getSendWorker",Employee.class,emp_idK9MW);
return aux;
}


}