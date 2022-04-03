package com.qidian.hcm.Interface;
public interface EmployeeRoleRepository {

   public List<EmployeeRole> findAllByEmployeeId(Long employeeId);
}