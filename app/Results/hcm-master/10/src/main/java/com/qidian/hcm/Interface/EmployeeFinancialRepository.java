package com.qidian.hcm.Interface;
public interface EmployeeFinancialRepository {

   public List<EmployeeFinancial> findBySocialSecurityPlanId(Long id);
}