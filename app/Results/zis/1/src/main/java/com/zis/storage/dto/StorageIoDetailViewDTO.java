package com.zis.storage.dto;
 import com.zis.storage.entity.StorageIoDetail;
public class StorageIoDetailViewDTO extends StorageIoDetail{

 private  String displayType;

 private  String displayAmount;

 private  String operatorName;


public void setDisplayType(String displayType){
    this.displayType = displayType;
}


public void init(){
    final String type = getIoDetailType();
    if (type != null) {
        displayType = IoType.getIoType(type).getDisplay();
    }
    if (IoType.OUT.getValue().equals(type)) {
        displayAmount = "-" + getAmount();
    } else {
        displayAmount = "+" + getAmount();
    }
}


public String getOperatorName(){
    return operatorName;
}


public String getDisplayType(){
    return displayType;
}


public void setDisplayAmount(String displayAmount){
    this.displayAmount = displayAmount;
}


public void setOperatorName(String operatorName){
    this.operatorName = operatorName;
}


public String getDisplayAmount(){
    return displayAmount;
}


}