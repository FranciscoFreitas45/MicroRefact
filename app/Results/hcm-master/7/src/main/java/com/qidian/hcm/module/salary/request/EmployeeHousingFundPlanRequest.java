package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "员工社保方案Request")
public class EmployeeHousingFundPlanRequest {

@ApiModelProperty(value = "公积金方案id")
@NotNull
@Min(1)
 private  Long housingFundPlanId;

@ApiModelProperty(value = "公积金账号")
@NotBlank
 private  String account;

@ApiModelProperty(value = "公积金基数")
@NotNull
@Min(0)
 private  Double housingFundBase;


}