package com.sobey.cmop.mvc.web.failure;
 import java.util.ArrayList;
import java.util.List;
public class StatusResponse {

 private  Boolean success;

 private  List<String> message;

public StatusResponse() {
    this.message = new ArrayList<String>();
}public StatusResponse(Boolean success) {
    super();
    this.success = success;
    this.message = new ArrayList<String>();
}public StatusResponse(Boolean success, String message) {
    super();
    this.success = success;
    this.message = new ArrayList<String>();
    this.message.add(message);
}public StatusResponse(Boolean success, List<String> message) {
    super();
    this.success = success;
    this.message = message;
}
public void setSuccess(Boolean success){
    this.success = success;
}


public List<String> getMessage(){
    return message;
}


@Override
public String toString(){
    StringBuilder sb = new StringBuilder();
    for (String mess : message) {
        sb.append(mess + ", ");
    }
    return "StatusResponse [success=" + success + ", message=" + sb.toString() + "]";
}


public void setMessage(String message){
    this.message.add(message);
}


public Boolean getSuccess(){
    return success;
}


}