package com.qidian.hcm.module.custom.request;
 import com.qidian.hcm.module.custom.enums.FieldType;
import com.qidian.hcm.module.custom.enums.TargetType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@ApiModel(value = "组织机构自定义字段")
@Getter
@Setter
public class OrgCustomizedFieldRequest {

@ApiModelProperty(value = "主键ID")
@Min(value = 1)
 private  Long id;

@ApiModelProperty(value = "序号,从0开始，字数越小，越靠前", required = true)
@NotNull(message = "idx不能为空")
@Min(value = 0, message = "idx值不能小于0")
 private  Integer idx;

@ApiModelProperty(value = "字段编码，同一所属内唯一", required = true)
@NotNull(message = "code不能为空")
@Size(min = 1, max = 45, message = "code的长度应该在1到45之间")
 private  String code;

@ApiModelProperty(value = "所属:company公司, department部门, position岗位, grade职级", required = true)
@NotNull(message = "targetType不能为空")
 private  TargetType targetType;

@ApiModelProperty(value = "字段类型:text_field单行文本，text_area多行文本，decimal数字框，select单选，" + "checkbox多选，date_range日期区间，date日期，file附件", required = true)
@NotNull(message = "fieldType不能为空")
@Enumerated(EnumType.STRING)
 private  FieldType fieldType;

@ApiModelProperty(value = "字段名称", required = true)
@NotNull(message = "label不能为空")
 private  String label;

@ApiModelProperty(value = "是否必填", required = true)
@NotNull(message = "required不能为空")
 private  boolean required;

@ApiModelProperty(value = "预制文案,当字段类型为文本框时必填")
 private  String placeholder;

@ApiModelProperty(value = "长度,当字段类型为文本框时必填")
@Min(value = 1, message = "length值不能小于1")
 private  Integer length;

@ApiModelProperty(value = "选项,当字段类型为下拉框或多选框时必填")
 private  List<String> options;


}