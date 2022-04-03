package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "调整薪资Request")
@EqualsAndHashCode(callSuper = true)
public class SalaryAdjustRequest extends SalaryEditRequest{

@ApiModelProperty(value = "薪资调整备注", required = true)
 private  String remark;


}