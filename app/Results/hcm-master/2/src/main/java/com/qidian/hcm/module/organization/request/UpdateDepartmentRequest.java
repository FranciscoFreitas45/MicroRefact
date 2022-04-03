package com.qidian.hcm.module.organization.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("部门请求对象")
public class UpdateDepartmentRequest extends CreateDepartmentRequest{

@ApiModelProperty(value = "主键（部门ID）", required = true)
 private  Long id;


}