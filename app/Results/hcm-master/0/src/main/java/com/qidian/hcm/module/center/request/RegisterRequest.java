package com.qidian.hcm.module.center.request;
 import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class RegisterRequest {

@NotBlank(message = "用户名不能为空！")
 private  String username;

@NotBlank(message = "密码不能为空！")
 private  String password;

@NotBlank(message = "手机不能为空！")
@Length(min = 11, max = 11)
 private  String phone;

@NotBlank(message = "分组名称不能为空！")
 private  String groupName;


}