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
@ApiModel(value = "岗位模块")
public class EmployeePositionInfoDTO {

@ApiModelProperty(value = "岗位信息")
@Size(min = 1)
@Valid
 private  List<EmployeePositionDTO> position;


}