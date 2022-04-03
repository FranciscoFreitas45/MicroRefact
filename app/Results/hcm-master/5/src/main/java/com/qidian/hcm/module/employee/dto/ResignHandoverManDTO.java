package com.qidian.hcm.module.employee.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("离职交接人")
public class ResignHandoverManDTO {

@ApiModelProperty(value = "交接人id")
 private  Long id;

@ApiModelProperty(value = "交接人姓名")
 private  String name;


}