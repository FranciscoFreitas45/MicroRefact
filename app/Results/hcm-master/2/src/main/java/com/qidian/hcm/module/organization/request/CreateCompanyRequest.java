package com.qidian.hcm.module.organization.request;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Getter
@Setter
@ApiModel
public class CreateCompanyRequest {

@ApiModelProperty(value = "公司代码", required = true)
@NotBlank(message = "公司代码不能为空")
@Pattern(regexp = "(^$|[0-9]{8})", message = "公司代码是8位数字")
 private  String code;

@ApiModelProperty(value = "公司名称", required = true)
@NotBlank(message = "公司名称不能为空")
@Length(min = 1, max = 100, message = "公司名称不能超过100个字符")
 private  String name;

@NotBlank(message = "公司简称不能为空")
@Length(min = 1, max = 100, message = "公司简称不能超过100个字符")
@ApiModelProperty(value = "公司简称")
 private  String alias;

@ApiModelProperty(value = "公司地址")
@Length(max = 200, message = "公司地址不能超过200个字符")
 private  String address;

@ApiModelProperty(value = "公司注册地址")
@Length(max = 200, message = "公司注册地址不能超过200个字符")
 private  String registerAddress;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@NotNull(message = "生效日期不能为空")
@ApiModelProperty(value = "生效日期", required = true)
 private  Date enableTime;

@NotNull(message = "上级公司不能为空")
@ApiModelProperty(value = "上级公司ID", required = true)
 private  Long parentId;

@ApiModelProperty(value = "公司总负责人")
 private  String master;

@ApiModelProperty(value = "公司法人代表")
 private  String legalPerson;

@ApiModelProperty(value = "自定义字段", required = true)
 private  JSONObject customField;


}