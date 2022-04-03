package com.qidian.hcm.module.salary.request;
 import com.qidian.hcm.module.salary.enums.EmployeesFilterType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@ApiModel("员工过滤条件")
@ToString
@Getter
@Setter
public class FilterEmployeesSalaryRequest {

@ApiModelProperty(value = "员工状态", required = true)
 private  EmployeesFilterType status;

@ApiModelProperty(value = "搜索关键字")
 private  String keyword;

@ApiModelProperty(value = "是否计薪")
@NotNull
 private  Boolean include;

@ApiModelProperty(value = "当前页码", required = true)
@NotNull
@Min(0)
 private  Integer pageNo;

@ApiModelProperty(value = "每页条数", required = true)
@NotNull
@Min(0)
 private  Integer pageSize;


}