package com.qidian.hcm.module.salary.response;
 import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "记薪配置（发薪日和发薪周期）Response")
public class SalarySettingResponse {

@ApiModelProperty(value = "首次薪资核算开始月份", required = true)
@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
 private  String firstCycleMonth;

@ApiModelProperty(value = "计薪周期开始日期，一般都是整月，1号开始")
 private  Integer startCycleDay;

@ApiModelProperty(value = "发薪日")
 private  Integer salaryPayDate;

@ApiModelProperty(value = "发薪月")
 private  String salaryPayMonth;


}