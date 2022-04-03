package com.qidian.hcm.module.employee.request;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.module.employee.enums.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Getter
@Setter
public class EmployeeBasicRequest {

@ApiModelProperty(value = "唯一标识", required = true)
 private  Long id;

@ApiModelProperty(value = "员工编号，员工唯一标识，不能重复", required = true)
@NotBlank(message = "员工编号不能为空")
 private  String employeeNo;

@NotBlank(message = "姓名不能为空")
@ApiModelProperty(value = "姓名", required = true)
 private  String name;

@ApiModelProperty(value = "头像", required = true)
 private  String avatar;

@ApiModelProperty(value = "头像名称", required = true)
 private  String avatarName;

@ApiModelProperty(value = "手机号，将作为员工登录账号", required = true)
@NotBlank(message = "手机号不能为空")
@Pattern(regexp = "(^$|[0-9]{11})")
 private  String mobile;

@ApiModelProperty(value = "性别，male男，female女", required = true)
@NotNull(message = "性别不能为空")
 private  Gender gender;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "生日", required = true)
@NotNull(message = "生日不能为空")
 private  Date birthday;


}