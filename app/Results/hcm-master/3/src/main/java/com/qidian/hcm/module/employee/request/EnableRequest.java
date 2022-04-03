package com.qidian.hcm.module.employee.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel
public class EnableRequest {

@ApiModelProperty(value = "enable true 生效，false：失效）", name = "enable", required = true)
@NotNull
 private  Boolean enable;


}