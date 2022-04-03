package com.qidian.hcm.module.salary.response;
 import com.qidian.hcm.module.salary.enums.ThresholdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "起征点Response")
public class SalaryThresholdResponse {

@ApiModelProperty(value = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "名称", required = true)
 private  String name;

@ApiModelProperty(value = "税点", required = true)
 private  Integer point;

@ApiModelProperty(value = "中国公民个人所得税 chinese 外籍人个人所得税foreign", required = true)
 private  ThresholdType type;


}