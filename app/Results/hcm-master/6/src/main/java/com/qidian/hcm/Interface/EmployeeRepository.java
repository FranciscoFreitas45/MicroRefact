package com.qidian.hcm.Interface;
public interface EmployeeRepository {

   public Object findById(Object Object);
   public Optional<Employee> findByEmployeeNo(String employeeNo);
}