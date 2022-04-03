package com.xwtec.xwserver.util.json;
 import org.apache.commons.lang.exception.NestableRuntimeException;
public class JSONException extends NestableRuntimeException{

 private  long serialVersionUID;

public JSONException() {
    super();
}public JSONException(String msg) {
    super(msg, null);
}public JSONException(String msg, Throwable cause) {
    super(msg, cause);
}public JSONException(Throwable cause) {
    super((cause == null ? null : cause.toString()), cause);
}
}