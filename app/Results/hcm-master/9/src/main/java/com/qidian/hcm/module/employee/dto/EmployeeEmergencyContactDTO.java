package com.qidian.hcm.module.employee.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@ApiModel(value = "紧急联系人")
public class EmployeeEmergencyContactDTO {

@ApiModelProperty(value = "只有更新时需要传参数", name = "id")
 private  Long id;

@ApiModelProperty(value = "姓名", required = true)
@NotBlank(message = "姓名不能为空")
 private  String name;

@ApiModelProperty(value = "关系", required = true)
@NotBlank(message = "姓名不能为空")
 private  String relationship;

@ApiModelProperty(value = "手机号", required = true)
@NotBlank(message = "手机号不能为空")
@Pattern(regexp = "(^$|[0-9]{11})")
 private  String mobile;


}