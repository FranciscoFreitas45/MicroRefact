package com.qidian.hcm.module.center.request;
 import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
public class UpdateUserPhoneRequest {

 private  Long userId;

@Length(min = 11, max = 11)
 private  String phone;


}