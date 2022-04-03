package org.live.common.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import org.live.common.constants.MessageConstants;
// 值为null的属性不参与序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleResponseModel implements ResponseModel<T>{

 private  int status;

 private  String message;

 private  T data;

public SimpleResponseModel() {
}public SimpleResponseModel(T data) {
    this.data = data;
}public SimpleResponseModel(int status, String message, T data) {
    this.setStatus(status);
    this.setMessage(message);
    this.setData(data);
}
@Override
public SimpleResponseModel setData(T data){
    this.data = data;
    return this;
}


@Override
public SimpleResponseModel success(String message){
    this.status = MessageConstants.OPERATION_SUCCESS_CODE;
    this.message = message;
    return this;
}


@Override
public String getMessage(){
    return this.message;
}


@Override
public int getStatus(){
    return status;
}


@Override
public SimpleResponseModel setMessage(String message){
    this.message = message;
    return this;
}


@Override
public SimpleResponseModel error(String message){
    status = MessageConstants.OPERATION_ERROR_CODE;
    this.message = message;
    return this;
}


@Override
public SimpleResponseModel setStatus(int status){
    this.status = status;
    return this;
}


@Override
public T getData(){
    return this.data;
}


}