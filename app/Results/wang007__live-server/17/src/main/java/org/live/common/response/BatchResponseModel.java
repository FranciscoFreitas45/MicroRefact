package org.live.common.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import org.live.common.constants.MessageConstants;
import java.util.ArrayList;
import java.util.List;
// 值为null的属性不参与序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchResponseModel implements ResponseModel<List>{

 private  int status;

 private  String message;

 private  List<?> data;

public BatchResponseModel() {
    data = new ArrayList<Object>();
}public BatchResponseModel(List data) {
    this.data = data;
}public BatchResponseModel(int status, String message, List data) {
    this.setStatus(status);
    this.setMessage(message);
    this.data = data;
}
@Override
public BatchResponseModel setData(List data){
    this.data = data;
    return this;
}


@Override
public BatchResponseModel success(String message){
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
    return this.status;
}


@Override
public BatchResponseModel setMessage(String message){
    this.message = message;
    return this;
}


@Override
public BatchResponseModel error(String message){
    this.status = MessageConstants.OPERATION_ERROR_CODE;
    this.message = message;
    return this;
}


@Override
public BatchResponseModel setStatus(int status){
    this.status = status;
    return this;
}


@Override
public List getData(){
    return this.data;
}


}