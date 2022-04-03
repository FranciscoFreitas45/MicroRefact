package com.qidian.hcm.module.employee.dto;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import com.qidian.hcm.module.employee.enums.EmployeeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "工作信息")
public class EmployeeJobDTO {

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "入职日期", name = "hire_date", required = true)
@NotNull(message = "入职日期不能为空")
 private  Date hireDate;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "开始工作日期", name = "work_date", required = true)
@NotNull(message = "入职日期不能为空")
 private  Date workDate;

@ApiModelProperty(value = "员工类型，trainee实习，labor劳务，laborContract劳务合同，" + "dispatch派遣，retirement退休返聘", name = "type", required = true)
@NotNull(message = "员工类型不能为空")
 private  EmployeeType type;

@ApiModelProperty(value = "员工状态，probation试用，formal正式, former离职", name = "status", required = true)
@NotNull(message = "员工状态不能为空")
 private  EmployeeStatus status;


}