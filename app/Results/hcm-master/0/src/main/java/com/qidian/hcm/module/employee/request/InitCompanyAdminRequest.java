package com.qidian.hcm.module.employee.request;
 import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class InitCompanyAdminRequest {

@NotBlank(message = "姓名不能为空！")
 private  String username;

@NotNull(message = "用户ID不能为空！")
 private  Long userId;

@NotBlank(message = "手机号不能为空！")
@Length(min = 11, max = 11)
 private  String phone;

@NotBlank(message = "分组名称不能为空！")
 private  String groupName;


}