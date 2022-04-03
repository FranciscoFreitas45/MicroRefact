package org.gliderwiki.web.vo.JsonResponse;
 import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
public class ResultStatusInfo implements Serializable{

 private  long serialVersionUID;

 private  String message;

 private  String redirectUrl;

 private  String errorMsg;


public void setErrorMsg(String errorMsg){
    this.errorMsg = errorMsg;
}


public String getMessage(){
    return message;
}


public String getRedirectUrl(){
    return redirectUrl;
}


public void setMessage(String message){
    this.message = message;
}


public void setRedirectUrl(String redirectUrl){
    this.redirectUrl = redirectUrl;
}


public String getErrorMsg(){
    return errorMsg;
}


}