package com.qidian.hcm.module.salary.dto;
 import io.swagger.annotations.ApiModel;
import lombok;
@Getter
@Setter
@ApiModel(value = "薪资项值明细DTO")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryMonthlyDTO {

 private  Long employeeId;

 private  String employeeNo;

 private  String employeeName;

 private  Boolean include;

 private  String itemsResult;


}