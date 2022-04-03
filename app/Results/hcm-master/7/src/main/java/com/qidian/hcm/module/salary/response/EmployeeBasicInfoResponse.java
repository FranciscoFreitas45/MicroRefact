package com.qidian.hcm.module.salary.response;
 import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import com.qidian.hcm.module.employee.enums.EmployeeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "薪资基本信息Response")
public class EmployeeBasicInfoResponse {

@ApiModelProperty(value = "员工编号")
 private  String employeeNo;

@ApiModelProperty(value = "头像")
 private  String avatar;

@ApiModelProperty(value = "姓名")
 private  String name;

@ApiModelProperty(value = "手机号")
 private  String mobile;

@ApiModelProperty(value = "入职日期")
 private  Date hireDate;

@ApiModelProperty(value = "员工类型")
 private  EmployeeType type;

@ApiModelProperty(value = "员工状态")
 private  EmployeeStatus status;

@ApiModelProperty(value = "公司")
 private  String company;

@ApiModelProperty(value = "部门")
 private  String department;

@ApiModelProperty(value = "岗位")
 private  String position;

@ApiModelProperty(value = "职级")
 private  String grade;

@ApiModelProperty(value = "薪酬")
 private  Double salary;


}