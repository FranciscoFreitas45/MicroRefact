package com.qidian.hcm.module.authorization.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@ApiModel("员工列表信息-包含角色信息")
public class EmployeeRoleDTO {

@ApiModelProperty(value = "员工ID")
 private  Long id;

@ApiModelProperty(value = "姓名")
 private  String employeeName;

@ApiModelProperty(value = "公司名称")
 private  String companyName;

@ApiModelProperty(value = "部门名称")
 private  String departmentName;

@ApiModelProperty(value = "手机号码")
 private  String mobile;

@ApiModelProperty(value = "角色列表")
 private  List<RoleDTO> roles;


}