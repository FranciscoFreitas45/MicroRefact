package com.qidian.hcm.module.employee.dto;
 import com.qidian.hcm.module.employee.enums.ContactType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ApiModel(value = "联系方式")
public class EmployeeContactDTO {

@ApiModelProperty(value = "只有更新时需要传参数", name = "id")
 private  Long id;

@ApiModelProperty(value = "联系类型，address居住地，residenceAddress户籍地址，homePhone座机，email邮箱", required = true)
@NotNull(message = "联系类型不能为空")
 private  ContactType type;

@ApiModelProperty(value = "类型内容", name = "content", required = true)
@NotBlank(message = "联系类型不能为空")
 private  String content;


}