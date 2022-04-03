package org.jeecgframework.jwt.util;
 import org.jeecgframework.jwt.util.menu.ResponseMessageCodeEnum;
public class Result {

 private  ResponseMessage RESPONSE_MESSAGE_SUCCESS;


public ResponseMessage<T> success(ResponseMessageCodeEnum codeEnum,String message,T t){
    return new ResponseMessage(codeEnum, message, true, t);
}


public ResponseMessage<T> errorValid(T t){
    return new ResponseMessage(ResponseMessageCodeEnum.VALID_ERROR, "校验失败", false, t);
}


public ResponseMessage<T> error(ResponseMessageCodeEnum codeEnum,String message,T t){
    return new ResponseMessage(codeEnum, message, false, t);
}


}