package com.qidian.hcm.module.salary.response;
 import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "薪酬历史记录Response")
public class EmployeeSalaryHistoryResponse {

@ApiModelProperty(value = "调薪前薪资")
 private  Double beforeAdjust;

@ApiModelProperty(value = "调薪后薪资")
 private  Double afterAdjust;

@ApiModelProperty(value = "调薪时间")
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 private  Date adjustDate;

@ApiModelProperty(value = "调薪类型，现在默认是basic")
 private  String type;

@ApiModelProperty(value = "涨幅百分比")
 private  Double increased;

@ApiModelProperty(value = "备注")
 private  String remark;


}