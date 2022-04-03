package com.qidian.hcm.module.center.request;
 import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class AddNotActiveUserRequest {

@NotBlank(message = "用户名不能为空！")
 private  String userName;

@NotBlank(message = "手机不能为空！")
@Length(min = 11, max = 11)
 private  String phone;

@NotNull(message = "tenantName！")
 private  String tenantName;


}