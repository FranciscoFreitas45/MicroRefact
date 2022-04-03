package com.qidian.hcm.module.salary.response;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "公积金方案数据Response")
public class EmployeeHousingFundPlanResponse {

@ApiModelProperty(value = "公积金账号")
 private  String account;

@ApiModelProperty(value = "公积金基数")
 private  Double housingFundBase;

@ApiModelProperty(value = "公积金方案id")
 private  Long housingFundPlanId;


}