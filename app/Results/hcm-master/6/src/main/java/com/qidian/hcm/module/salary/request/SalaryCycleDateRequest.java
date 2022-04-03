package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@ApiModel(value = "发薪周期Request")
public class SalaryCycleDateRequest {

@ApiModelProperty(value = "首次薪资核算开始月份,格式: yyyy-MM", required = true, example = "2018-09")
@Pattern(regexp = "^((?:19|20)\\d\\d)-(0[1-9]|1[012])$", message = "日期格式必须为yyyy-MM")
@NotNull
 private  String firstCycleMonth;

@ApiModelProperty(value = "计薪周期开始日期", required = true, example = "1")
@Range(min = 1, max = 28, message = "计薪周期开始日期不正确")
@NotNull
 private  Integer startCycleDay;


}