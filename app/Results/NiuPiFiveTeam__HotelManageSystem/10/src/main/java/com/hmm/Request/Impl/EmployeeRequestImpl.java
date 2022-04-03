package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.Employee;
import com.hmm.Request.EmployeeRequest;
public class EmployeeRequestImpl implements EmployeeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setRoomWorker(Employee roomWorker,Integer emp_idU6JN){
 restTemplate.put("http://16/RoomCleanRecord/{id}/Employee/setRoomWorker",roomWorker,emp_idU6JN);
 return ;
}


public Employee getRoomWorker(Integer emp_idU6JN){
 Employee aux = restTemplate.getForObject("http://16/RoomCleanRecord/{id}/Employee/getRoomWorker",Employee.class,emp_idU6JN);
return aux;
}


}