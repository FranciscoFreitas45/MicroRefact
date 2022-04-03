package com.zis.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
public class StorageIoDetail {

 public  String SORT_POS_ID;

 public  String SORT_CREATE_TIME;

 private  Integer detailId;

 private  Integer batchId;

 private  Integer repoId;

 private  Integer orderId;

 private  Integer posId;

 private  String posLabel;

 private  Integer skuId;

 private  Integer productId;

 private  Integer amount;

 private  Integer balance;

 private  Integer operator;

 private  String ioDetailType;

 private  String detailStatus;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

 private  String value;

 private  String display;

 private  String value;

 private  String display;


public Integer getRepoId(){
    return repoId;
}


public Integer getSkuId(){
    return this.skuId;
}


public Date getGmtCreate(){
    return this.gmtCreate;
}


public Integer getOrderId(){
    return orderId;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public Integer getProductId(){
    return productId;
}


public void setOperator(Integer operator){
    this.operator = operator;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public Integer getDetailId(){
    return this.detailId;
}


public Integer getOperator(){
    return operator;
}


public Integer getAmount(){
    return this.amount;
}


public Integer getVersion(){
    return this.version;
}


public String getIoDetailType(){
    return ioDetailType;
}


public Integer getPosId(){
    return posId;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public Date getGmtModify(){
    return this.gmtModify;
}


public DetailStatus getDetailStatus(String status){
    for (DetailStatus st : DetailStatus.values()) {
        if (st.getValue().equals(status))
            return st;
    }
    return null;
}


public String getValue(){
    return value;
}


public Integer getBalance(){
    return balance;
}


public Integer getBatchId(){
    return batchId;
}


public String getPosLabel(){
    return posLabel;
}


public String getDisplay(){
    return display;
}


public IoType getIoType(String type){
    for (IoType st : IoType.values()) {
        if (st.getValue().equals(type))
            return st;
    }
    return null;
}


}