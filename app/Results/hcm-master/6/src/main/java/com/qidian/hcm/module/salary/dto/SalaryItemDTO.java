package com.qidian.hcm.module.salary.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "薪资项DTO")
@AllArgsConstructor
public class SalaryItemDTO {

@ApiModelProperty(value = "编码", required = true)
 private  String code;

@ApiModelProperty(value = "名称", required = true)
 private  String name;


}