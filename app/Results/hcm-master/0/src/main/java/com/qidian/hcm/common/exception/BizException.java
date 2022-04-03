package com.qidian.hcm.common.exception;
 import com.qidian.hcm.common.utils.ResultCode;
import lombok.Getter;
@Getter
public class BizException extends RuntimeException{

 private  String errorCode;

 private  String message;

 private  Object data;

public BizException(ResultCode resultCode) {
    super();
    this.errorCode = resultCode.getCode();
    this.message = resultCode.getMsg();
}public BizException(ResultCode resultCode, Object data) {
    super();
    this.errorCode = resultCode.getCode();
    this.message = resultCode.getMsg();
    this.data = data;
}
}