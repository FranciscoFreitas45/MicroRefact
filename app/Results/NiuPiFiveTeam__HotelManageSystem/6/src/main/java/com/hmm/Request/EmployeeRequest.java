package com.hmm.Request;
import com.hmm.DTO.Employee;
public interface EmployeeRequest {

   public void setEmploy(Employee employ,Integer emp_idPBMQ);
   public Employee getEmploy(Integer emp_idPBMQ);
}