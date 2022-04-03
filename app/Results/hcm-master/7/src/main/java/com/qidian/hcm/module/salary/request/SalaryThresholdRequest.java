package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "修改起征点Request")
public class SalaryThresholdRequest {

@ApiModelProperty(value = "起征点", required = true)
 private  Integer point;


}