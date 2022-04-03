package com.qidian.hcm.module.employee.response;
 import com.qidian.hcm.module.employee.dto.FormFieldDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@ApiModel("自定义表单信息")
public class CustomizedFormResponse {

@ApiModelProperty(value = "表单ID")
 private  Long id;

@ApiModelProperty(value = "表单编码")
 private  String code;

@ApiModelProperty(value = "表单名称")
 private  String title;

@ApiModelProperty(value = "是否支持多条记录")
 private  boolean multiRecord;

@ApiModelProperty(value = "模块是否必填")
 private  boolean required;

@ApiModelProperty(value = "员工入职是否启用：0否，1是")
 private  boolean onBoard;

@ApiModelProperty(value = "表单字段")
 private  List<FormFieldDTO> fields;


}