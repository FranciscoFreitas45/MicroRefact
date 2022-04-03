package com.hmm.Interface;
public interface EmployeeDao {

   public Object save(Object Object);
   public Object existsById(Object Object);
   public Object deleteById(Object Object);
   public Object findAll(Object Object);
   public Object count(Object Object);
   public Object findAllById(Object Object);
   public Object deleteAll(Object Object);
   public Employee findByEmpNo(String empNo);
   public Object findById(Object Object);
   public Employee findByEmpNameAndEmpNo(String empName,String empNo);
   public Employee findByEmpName(String empName);
   public void updatePassword(String password,String userName);
   public Employee findByUserName(String userName);
}