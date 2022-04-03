package com.qidian.hcm.module.employee.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@ApiModel(value = "其他模块")
public class EmployeeOtherInfoDTO {

@ApiModelProperty(value = "联系信息")
@Size(min = 1)
@Valid
 private  List<EmployeeContactDTO> contact;

@ApiModelProperty(value = "紧急联系人")
@Size(min = 1)
@Valid
 private  List<EmployeeEmergencyContactDTO> emergencyContact;

@ApiModelProperty(value = "教育信息")
@Size(min = 1)
@Valid
 private  List<EmployeeEducationDTO> education;

@ApiModelProperty(value = "工作经历")
@Valid
 private  List<EmployeeWorkExperienceDTO> workExperience;


}