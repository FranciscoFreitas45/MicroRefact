package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "薪资项值明细Request")
public class SalaryItemMonthlyRequest {

@ApiModelProperty(value = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "值")
 private  Double value;

@ApiModelProperty(value = "编码")
 private  String code;

@ApiModelProperty(value = "是否修改过，默认为false", required = true)
 private  Boolean modified;


}