package com.qidian.hcm.module.authorization.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

@ApiModelProperty(value = "角色ID", name = "角色ID")
 private  Long id;

@ApiModelProperty(value = "角色名称", name = "角色名称")
 private  String name;


}