package com.qidian.hcm.module.employee.request;
 import com.qidian.hcm.module.employee.enums.FormType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Slf4j
@Getter
@Setter
@ApiModel("增加自定义模块")
public class CustomizedFormsRequest {

@ApiModelProperty(value = "模板名称")
@NotBlank(message = "模板名称不能为空")
 private  String title;

@ApiModelProperty(value = "是否支持多条记录")
@NotNull
 private  Boolean multiRecord;

@ApiModelProperty(value = "模块是否必填")
@NotNull
 private  Boolean required;

@ApiModelProperty(value = "员工入职是否启用")
@NotNull
 private  Boolean onBoard;

@ApiModelProperty(value = "索引")
 private  Integer idx;

@ApiModelProperty(value = "模块分类,position岗位信息，basic基本信息,other其他信息")
 private  FormType type;

@ApiModelProperty(value = "自定义字段列表")
 private  List<CustomizedFormsFieldsRequest> fields;


}