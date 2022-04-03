package com.qidian.hcm.module.center.request;
 import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ApiModel("登陆请求")
public class LoginRequest {

@NotBlank(message = "用户名不能为空")
 private  String username;

@NotBlank(message = "密码不能为空")
 private  String password;


}