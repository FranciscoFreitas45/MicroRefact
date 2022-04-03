package com.qidian.hcm.module.salary.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@ApiModel(value = "员工ID")
public class EmployeeListRequest {

@ApiModelProperty(value = "员工ID数组", required = true)
@NotNull
@Size(min = 1)
 private  List<Long> ids;


}