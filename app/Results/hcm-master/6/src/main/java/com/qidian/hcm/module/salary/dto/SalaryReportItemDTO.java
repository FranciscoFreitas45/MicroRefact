package com.qidian.hcm.module.salary.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "薪资项报表值Value")
public class SalaryReportItemDTO {

@ApiModelProperty(value = "薪资项编码")
 private  String code;

@ApiModelProperty(value = "薪资项名称")
 private  String name;

@ApiModelProperty(value = "薪资项对应的值", required = true)
 private  Double value;


}