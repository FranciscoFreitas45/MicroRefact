package com.qidian.hcm.module.salary.response;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "员工-基本信息Response")
public class EmployeeSecurityDetailResponse {

@ApiModelProperty(value = "社保方案")
 private  EmployeeSecurityPlanResponse employeeSecurityPlan;

@ApiModelProperty(value = "公积金方案")
 private  EmployeeHousingFundPlanResponse employeeHousingFundPlan;


}