package com.qidian.hcm.module.salary.response;
 import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "薪酬分页Response")
@NoArgsConstructor
public class EmployeeSalaryPageResponse {

@Id
@ApiModelProperty(value = "id")
 private  Long id;

@ApiModelProperty(value = "员工名称")
 private  String name;

@ApiModelProperty(value = "头像")
@Transient
 private  String avatar;

 private  Long avatarFileId;

@ApiModelProperty(value = "公司名称")
@Transient
 private  String companyName;

@ApiModelProperty(value = "部门名称")
@Transient
 private  String departmentName;

@ApiModelProperty(value = "员工编号")
 private  String employeeNo;

@ApiModelProperty(value = "状态")
@Enumerated(EnumType.STRING)
 private  EmployeeStatus status;

@ApiModelProperty(value = "入职日期")
 private  Date hireDate;

@ApiModelProperty(value = "离职日期")
 private  Date resignationDate;

@ApiModelProperty(value = "薪酬")
 private  Double salary;


}