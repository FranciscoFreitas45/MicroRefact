package com.hmm.Request;
import com.hmm.DTO.Employee;
public interface EmployeeRequest {

   public void setEmployee(Set<Employee> employ,Integer dept_id);
   public Set<Employee> getEmployee(Integer dept_id);
}