package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EmployeeSalaryMonthlyServiceController {

 private EmployeeSalaryMonthlyService employeesalarymonthlyservice;


@GetMapping
("/getSalaryMonthly")
public List<SalaryItemMonthlyResponse> getSalaryMonthly(@RequestParam(name = "employeeId") Long employeeId){
  return employeesalarymonthlyservice.getSalaryMonthly(employeeId);
}


@GetMapping
("/getEmployeeAccounting")
public List<SalaryItemAccountingDTO> getEmployeeAccounting(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "salaryItemMonthlyRequest") SalaryItemAccountingDTO salaryItemMonthlyRequest){
  return employeesalarymonthlyservice.getEmployeeAccounting(employeeId,salaryItemMonthlyRequest);
}


@PutMapping
("/saveDetailInfo")
public void saveDetailInfo(@RequestParam(name = "employeeId") Long employeeId,@RequestParam(name = "salaryItemMonthlyRequestList") List<SalaryItemMonthlyRequest> salaryItemMonthlyRequestList){
employeesalarymonthlyservice.saveDetailInfo(employeeId,salaryItemMonthlyRequestList);
}


@PutMapping
("/salaryExclude")
public void salaryExclude(@RequestParam(name = "employeeListRequest") EmployeeListRequest employeeListRequest){
employeesalarymonthlyservice.salaryExclude(employeeListRequest);
}


@PutMapping
("/salaryInclude")
public void salaryInclude(@RequestParam(name = "employeeListRequest") EmployeeListRequest employeeListRequest){
employeesalarymonthlyservice.salaryInclude(employeeListRequest);
}


@GetMapping
("/getEmployeeSalaryPage")
public Page<EmployeeSalaryPageResponse> getEmployeeSalaryPage(@RequestParam(name = "filterEmployeesSalary") FilterEmployeesSalaryRequest filterEmployeesSalary){
  return employeesalarymonthlyservice.getEmployeeSalaryPage(filterEmployeesSalary);
}


}