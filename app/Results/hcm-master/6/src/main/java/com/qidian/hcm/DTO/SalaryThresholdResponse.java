package com.qidian.hcm.DTO;
 import com.qidian.hcm.module.salary.enums.ThresholdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
public class SalaryThresholdResponse {

 private  Long id;

 private  String name;

 private  Integer point;

 private  ThresholdType type;


}