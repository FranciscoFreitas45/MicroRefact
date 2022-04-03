package com.qidian.hcm.module.salary.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "薪资项计算结果返回DTO")
public class SalaryItemAccountingDTO {

@ApiModelProperty(value = "编码", required = true)
 private  String code;

@ApiModelProperty(value = "值", required = true)
 private  Double value;


}