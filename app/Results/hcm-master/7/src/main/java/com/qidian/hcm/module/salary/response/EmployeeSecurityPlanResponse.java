package com.qidian.hcm.module.salary.response;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "社保方案response")
public class EmployeeSecurityPlanResponse {

@ApiModelProperty(value = "方案名称")
 private  Long socialSecurityPlanId;

@ApiModelProperty(value = "社保基数")
 private  Double socialSecurityBase;


}