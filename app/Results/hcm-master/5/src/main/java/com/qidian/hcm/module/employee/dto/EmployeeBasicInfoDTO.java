package com.qidian.hcm.module.employee.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@ApiModel(value = "员工基本信息")
public class EmployeeBasicInfoDTO {

@ApiModelProperty(value = "工作信息")
@NotNull
@Valid
 private  EmployeeJobDTO job;

@ApiModelProperty(value = "合同信息")
@Size(min = 1)
@Valid
 private  List<EmployeeContractDTO> contract;

@ApiModelProperty(value = "证件信息")
@Size(min = 1)
@Valid
 private  List<EmployeeIdentityDTO> identity;


}