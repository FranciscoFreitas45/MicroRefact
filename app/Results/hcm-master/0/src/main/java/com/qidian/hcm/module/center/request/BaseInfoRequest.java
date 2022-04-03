package com.qidian.hcm.module.center.request;
 import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ApiModel("基本信息")
public class BaseInfoRequest {

 private  String email;

@NotBlank(message = "密码不能为空")
 private  String password;


}