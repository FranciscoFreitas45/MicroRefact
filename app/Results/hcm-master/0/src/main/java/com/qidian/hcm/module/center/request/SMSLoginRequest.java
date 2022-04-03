package com.qidian.hcm.module.center.request;
 import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ApiModel("手机登陆")
public class SMSLoginRequest {

@NotBlank(message = "手机号")
 private  String phone;

@NotBlank(message = "验证码")
 private  String code;


}