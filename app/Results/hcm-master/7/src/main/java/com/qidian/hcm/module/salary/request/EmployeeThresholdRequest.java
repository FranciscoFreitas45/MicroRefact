package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "免税额类型Request")
public class EmployeeThresholdRequest {

@ApiModelProperty(value = "id")
@NotNull
@Min(0)
 private  Long id;


}