package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "编辑薪资Request")
public class SalaryEditRequest {

@ApiModelProperty(value = "金额数值", required = true)
@NotNull
@Min(1)
 private  Double salary;


}