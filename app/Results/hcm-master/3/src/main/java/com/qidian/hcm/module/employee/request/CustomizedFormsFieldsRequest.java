package com.qidian.hcm.module.employee.request;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.custom.enums.FieldType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("增加自定义字段类型")
public class CustomizedFormsFieldsRequest {

@ApiModelProperty(value = "自定义字段类型")
 private  FieldType fieldType;

@ApiModelProperty(value = "id(创建的时候不加)")
 private  Long id;

 private  JSONObject attribute;


}