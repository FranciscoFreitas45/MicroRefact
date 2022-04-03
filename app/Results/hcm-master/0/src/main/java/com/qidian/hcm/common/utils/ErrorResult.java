package com.qidian.hcm.common.utils;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
public class ErrorResult {

 private  String code;

 private  String message;

 private  Object error;

public ErrorResult(ResultCode resultCode, Object error) {
    this.code = resultCode.getCode();
    this.message = resultCode.getMsg();
    this.error = error;
}
}