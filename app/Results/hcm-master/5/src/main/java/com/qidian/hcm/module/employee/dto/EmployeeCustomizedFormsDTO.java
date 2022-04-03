package com.qidian.hcm.module.employee.dto;
 import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "员工自定义表单")
public class EmployeeCustomizedFormsDTO {

@ApiModelProperty(value = "自定义表单Id", required = true)
@NotNull(message = "自定义表单Id不能为空")
 private  Long formId;

@ApiModelProperty(value = "数据对象", required = true)
@NotNull(message = "数据对象不能为空")
 private  JSONArray formData;


}