package com.empl.mgr.Interface;
public interface DepartmentDao {

   public Object findUniqueByProperty(Object Object);
   public List<DepartmentSelectDto> findAllDepartment();
   public Object findById(Object Object);
}