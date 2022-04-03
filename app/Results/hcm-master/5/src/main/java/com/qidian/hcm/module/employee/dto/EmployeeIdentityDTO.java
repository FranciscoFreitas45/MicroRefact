package com.qidian.hcm.module.employee.dto;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.employee.enums.IdentityType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "证件信息")
public class EmployeeIdentityDTO {

@ApiModelProperty(value = "只有更新时需要传参数", name = "id")
 private  Long id;

@ApiModelProperty(value = "证件类型：idCard:身份证;passport:护照", name = "type", required = true)
@NotNull(message = "证件类型不能为空")
 private  IdentityType type;

@Column(name = "code")
@ApiModelProperty(value = "证件号码", name = "code", required = true)
@NotNull(message = "证件号码不能为空")
 private  String code;

@Transient
@ApiModelProperty(value = "自定义字段", name = "customizedFields", required = true)
 private  JSONObject customizedFields;


}