package com.qidian.hcm.module.employee.request;
 import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("员工过滤条件")
public class FilterEmployeeRequest {

@ApiModelProperty(value = "员工状态: probation试用, formal正式, former离职", required = true)
 private  EmployeeStatus status;

@ApiModelProperty(value = "搜索关键字")
 private  String keyword;

@ApiModelProperty(value = "当前页码", required = true)
 private  Integer pageNo;

@ApiModelProperty(value = "每页条数", required = true)
 private  Integer pageSize;


}