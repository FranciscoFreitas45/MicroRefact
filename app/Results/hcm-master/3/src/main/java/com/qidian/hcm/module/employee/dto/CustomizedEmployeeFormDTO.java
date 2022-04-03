package com.qidian.hcm.module.employee.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "自定义表单")
public class CustomizedEmployeeFormDTO {

@ApiModelProperty(required = true)
 private  Long id;

@ApiModelProperty(value = "模块名称", required = true)
 private  String title;

@ApiModelProperty(value = "是否必填", required = true)
 private  Boolean required;

@ApiModelProperty(value = "是否支持多个", required = true)
 private  Boolean multiRecord;

@ApiModelProperty(value = "是否在员工入职的时候启用", required = true)
 private  Boolean onBoard;

@ApiModelProperty(value = "是否可以切换开启状态", required = true)
 private  Boolean switchable;

@ApiModelProperty(value = "是否启用", required = true)
 private  Boolean enable;

@ApiModelProperty(value = "排序", required = true)
 private  Integer idx;

@ApiModelProperty(value = "是否可以移除", required = true)
 private  Boolean deletable;


}