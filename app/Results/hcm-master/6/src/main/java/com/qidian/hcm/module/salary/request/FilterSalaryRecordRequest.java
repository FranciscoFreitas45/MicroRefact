package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel("归档记录过滤条件")
public class FilterSalaryRecordRequest {

@ApiModelProperty(value = "当前页码", required = true)
@NotNull(message = "当前页码不能为空")
 private  Integer pageNo;

@ApiModelProperty(value = "每页条数", required = true)
@NotNull(message = "pageSize不能为空")
 private  Integer pageSize;


}