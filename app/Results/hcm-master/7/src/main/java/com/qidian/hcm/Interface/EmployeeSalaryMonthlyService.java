package com.qidian.hcm.Interface;
public interface EmployeeSalaryMonthlyService {

   public List<SalaryItemMonthlyResponse> getSalaryMonthly(Long employeeId);
   public List<SalaryItemAccountingDTO> getEmployeeAccounting(Long employeeId,SalaryItemAccountingDTO salaryItemMonthlyRequest);
   public void saveDetailInfo(Long employeeId,List<SalaryItemMonthlyRequest> salaryItemMonthlyRequestList);
   public void salaryExclude(EmployeeListRequest employeeListRequest);
   public void salaryInclude(EmployeeListRequest employeeListRequest);
   public Page<EmployeeSalaryPageResponse> getEmployeeSalaryPage(FilterEmployeesSalaryRequest filterEmployeesSalary);
}