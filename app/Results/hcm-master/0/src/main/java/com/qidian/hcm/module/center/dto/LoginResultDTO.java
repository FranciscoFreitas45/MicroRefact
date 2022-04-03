package com.qidian.hcm.module.center.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "验证码登陆结果")
public class LoginResultDTO {

@ApiModelProperty(value = "isActive", name = "isActive")
 private  Boolean isActive;

@ApiModelProperty(value = "名称", name = "content")
 private  String token;

@ApiModelProperty(value = "userId", name = "userId")
 private  Long userId;


}