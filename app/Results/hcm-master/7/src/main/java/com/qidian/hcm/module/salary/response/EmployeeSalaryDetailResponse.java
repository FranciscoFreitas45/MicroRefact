package com.qidian.hcm.module.salary.response;
 import com.qidian.hcm.module.salary.dto.BankInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "员工-薪酬信息tab页面")
public class EmployeeSalaryDetailResponse {

@ApiModelProperty(value = "银行卡信息")
 private  BankInfoDTO bankInfo;

@ApiModelProperty(value = "免税额")
 private  SalaryThresholdResponse threshold;


}