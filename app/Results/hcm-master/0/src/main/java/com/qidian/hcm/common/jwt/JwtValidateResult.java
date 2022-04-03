package com.qidian.hcm.common.jwt;
 import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JwtValidateResult {

 private  TokenState state;

 private  T data;


}