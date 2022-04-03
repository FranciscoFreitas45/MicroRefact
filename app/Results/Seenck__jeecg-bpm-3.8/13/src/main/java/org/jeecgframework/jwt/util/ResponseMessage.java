package org.jeecgframework.jwt.util;
 import org.jeecgframework.jwt.util.menu.ResponseMessageCodeEnum;
public class ResponseMessage {

 private  String respCode;

 private  String respMsg;

 private  T data;

 private  boolean ok;

public ResponseMessage() {
}public ResponseMessage(ResponseMessageCodeEnum codeEnum, String message) {
    this.respCode = codeEnum.getCode();
    this.respMsg = message;
}public ResponseMessage(ResponseMessageCodeEnum codeEnum, String message, boolean ok, T data) {
    this.respCode = codeEnum.getCode();
    this.respMsg = message;
    this.ok = ok;
    this.data = data;
}
public void setData(T data){
    this.data = data;
}


public boolean isOk(){
    return ok;
}


public String getMessage(){
    return respMsg;
}


public void setRespCode(String respCode){
    this.respCode = respCode;
}


public void setMessage(String message){
    this.respMsg = message;
}


public String getRespCode(){
    return respCode;
}


public T getData(){
    return data;
}


}