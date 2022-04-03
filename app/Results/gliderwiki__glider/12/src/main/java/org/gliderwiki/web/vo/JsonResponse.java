package org.gliderwiki.web.vo;
 import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
public class JsonResponse implements Serializable{

 private  long serialVersionUID;

 private  ResponseStatus status;

 private  ResultStatusInfo response;

 private  Object result;

 private  long serialVersionUID;

 private  String message;

 private  String redirectUrl;

 private  String errorMsg;

 private  String description;


public void setResult(Object result){
    this.result = result;
}


public ResultStatusInfo getResponse(){
    return response;
}


public String getMessage(){
    return message;
}


public void setResponse(ResultStatusInfo response){
    this.response = response;
}


public ResponseStatus getStatus(){
    return status;
}


public void setMessage(String message){
    this.message = message;
}


public String getDescription(){
    return description;
}


public void setStatus(ResponseStatus status){
    this.status = status;
}


public void setErrorMsg(String errorMsg){
    this.errorMsg = errorMsg;
}


public Object getResult(){
    return result;
}


public String toString(){
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}


public String getRedirectUrl(){
    return redirectUrl;
}


public void setRedirectUrl(String redirectUrl){
    this.redirectUrl = redirectUrl;
}


public String getErrorMsg(){
    return errorMsg;
}


}