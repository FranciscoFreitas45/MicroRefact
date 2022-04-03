package com.qidian.hcm.module.salary.response;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "薪资项选项Response")
public class SalaryItemsOptionResponse {

@ApiModelProperty(value = "id")
 private  Long id;

@ApiModelProperty(value = "名称")
 private  String name;

@ApiModelProperty(value = "编码")
 private  String code;


}