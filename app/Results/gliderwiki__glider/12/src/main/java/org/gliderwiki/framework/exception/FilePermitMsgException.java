package org.gliderwiki.framework.exception;
 public class FilePermitMsgException extends RuntimeException{

 private  long serialVersionUID;

 private  String customMsg;

// 생성자
public FilePermitMsgException(String customMsg) {
    this.customMsg = customMsg;
}
public String getCustomMsg(){
    return customMsg;
}


public void setCustomMsg(String customMsg){
    this.customMsg = customMsg;
}


}