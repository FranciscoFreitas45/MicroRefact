package com.qidian.hcm.module.salary.request;
 import com.qidian.hcm.module.salary.enums.SalaryPayMonth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "发薪日Request")
public class SalaryDateRequest {

@ApiModelProperty(value = "发薪月", required = true)
@NotNull(message = "发薪月不能为空，0:本月;1:次月")
 private  SalaryPayMonth salaryPayMonth;

@ApiModelProperty(value = "发薪日期,取值为1-31", required = true)
@NotNull
@Range(min = 1, max = 31, message = "发薪日期参数范围不正确")
 private  Integer salaryPayDate;


}