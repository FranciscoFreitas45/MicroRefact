package com.qidian.hcm.module.authorization.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel
public class RoleEditRequest {

@ApiModelProperty(value = "角色名称", name = "角色名称", required = true)
 private  String name;


}