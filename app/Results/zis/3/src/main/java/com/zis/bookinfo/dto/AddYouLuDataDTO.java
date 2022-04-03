package com.zis.bookinfo.dto;
 import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
public class AddYouLuDataDTO {

@Min(value = 1, message = "开始ID不能小于1")
@NotNull(message = "开始ID不能为空")
 private  Integer startId;

@Min(value = 1, message = "结束ID不能小于1")
@NotNull(message = "结束ID不能为空")
 private  Integer finalId;

 private  String operateType;


public void setStartId(Integer startId){
    this.startId = startId;
}


public String getOperateType(){
    return operateType;
}


public Integer getFinalId(){
    return finalId;
}


public void setFinalId(Integer finalId){
    this.finalId = finalId;
}


public void setOperateType(String operateType){
    this.operateType = operateType;
}


public Integer getStartId(){
    return startId;
}


}