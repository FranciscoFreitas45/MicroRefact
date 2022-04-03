package com.qidian.hcm.module.employee.dto;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qidian.hcm.module.custom.enums.FieldType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("表单字段")
public class FormFieldDTO {

@ApiModelProperty(value = "字段ID")
 private  Long id;

@ApiModelProperty(value = "表单ID", hidden = true)
@JsonIgnore
 private  Long formId;

@ApiModelProperty(value = "字段类型")
 private  FieldType fieldType;

@ApiModelProperty(value = "字段排序")
 private  Integer idx;

@ApiModelProperty(value = "字段属性")
 private  JSONObject attribute;


}