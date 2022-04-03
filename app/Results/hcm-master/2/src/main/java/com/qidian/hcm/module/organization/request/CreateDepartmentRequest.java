package com.qidian.hcm.module.organization.request;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.common.enums.YesNo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Getter
@Setter
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentRequest {

@NotBlank(message = "部门名称不能为空")
@Length(min = 1, max = 100, message = "部门名称不能超过100个字符")
@ApiModelProperty(value = "部门名称", required = true)
 private  String name;

@ApiModelProperty(value = "部门代码", required = true)
@NotBlank(message = "部门代码不能为空")
@Pattern(regexp = "(^$|[0-9]{8})", message = "部门代码是8位数字")
 private  String code;

@NotBlank(message = "部门简称不能为空")
@Length(min = 1, max = 100, message = "部门简称长度必须在1到100个字符之间")
@ApiModelProperty(value = "部门简称", required = true)
 private  String alias;

@ApiModelProperty(value = "所属公司或部门ID", required = true)
@NotNull(message = "所属公司或部门不能为空")
 private  Long parentId;

@ApiModelProperty(value = "编制")
 private  Integer formation;

@ApiModelProperty(value = "是否有效", required = true)
 private  YesNo enable;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "生效日期", required = true)
@NotNull(message = "生效日期不能为空")
 private  Date enableTime;

@ApiModelProperty(value = "自定义字段")
 private  JSONObject customField;

@ApiModelProperty(value = "部门负责人")
 private  String master;


}