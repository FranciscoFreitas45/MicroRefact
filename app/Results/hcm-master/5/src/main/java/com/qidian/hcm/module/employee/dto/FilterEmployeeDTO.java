package com.qidian.hcm.module.employee.dto;
 import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
@Getter
@Setter
@ApiModel("员工列表信息")
public class FilterEmployeeDTO {

@Id
@ApiModelProperty(value = "员工ID")
 private  Long id;

@ApiModelProperty(value = "工号")
 private  String employeeNo;

@ApiModelProperty(value = "姓名")
 private  String name;

@Transient
@ApiModelProperty(value = "头像oss地址")
 private  String avatar;

@ApiModelProperty(value = "头像file id")
 private  Long avatarFileId;

@ApiModelProperty(value = "公司名称")
 private  String companyName;

@ApiModelProperty(value = "部门名称")
 private  String departmentName;

@ApiModelProperty(value = "岗位名称")
 private  String positionName;

@ApiModelProperty(value = "职级名称")
 private  String gradeName;

@ApiModelProperty(value = "离职日期")
 private  Date resignationDate;

@ApiModelProperty(value = "离职原因")
 private  String resignationReason;

@ApiModelProperty(value = "状态")
 private  EmployeeStatus status;

@ApiModelProperty(value = "是否允许编辑")
 private  boolean enableEdit;


}