package com.qidian.hcm.module.employee.response;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
@Getter
@AllArgsConstructor
@ApiModel("员工入职自定义表单布局信息")
public class CustomizedFormLayoutResponse {

@ApiModelProperty(value = "岗位信息")
 private  List<CustomizedFormResponse> position;

@ApiModelProperty(value = "基本信息")
 private  List<CustomizedFormResponse> basic;

@ApiModelProperty(value = "其他信息")
 private  List<CustomizedFormResponse> other;


}