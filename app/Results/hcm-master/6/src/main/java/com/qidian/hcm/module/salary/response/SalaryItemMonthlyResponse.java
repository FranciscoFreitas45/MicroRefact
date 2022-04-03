package com.qidian.hcm.module.salary.response;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "薪资项值明细Response")
public class SalaryItemMonthlyResponse {

@ApiModelProperty(value = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "编码")
 private  String code;

@ApiModelProperty(value = "名称")
 private  String name;

@ApiModelProperty(value = "值", required = true)
 private  Double value;

@ApiModelProperty(value = "是否修改过，默认为false", required = true)
 private  Boolean modified;


}