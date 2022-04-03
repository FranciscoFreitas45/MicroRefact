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
public class StorageProduct {

 private  Integer productId;

 private  Integer skuId;

 private  String skuName;

 private  Integer repoId;

 private  Integer subjectId;

 private  String subjectName;

 private  Integer stockAmt;

 private  Integer stockOccupy;

 private  Integer stockAvailable;

 private  Boolean lockFlag;

 private  String lockReason;

 private  Date gmtCreate;

 private  Date gmtModify;

 private  Integer version;

// Constructors
/**
 * default constructor
 */
public StorageProduct() {
}
public Integer getRepoId(){
    return repoId;
}


public Integer getSkuId(){
    return skuId;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Integer getStockOccupy(){
    return stockOccupy;
}


public Boolean getLockFlag(){
    return lockFlag;
}


public Integer getProductId(){
    return productId;
}


public Integer getVersion(){
    return version;
}


public Integer getSubjectId(){
    return subjectId;
}


public String getLockReason(){
    return lockReason;
}


public Integer getStockAmt(){
    return stockAmt;
}


public Date getGmtModify(){
    return gmtModify;
}


public String getSkuName(){
    return skuName;
}


public String getSubjectName(){
    return subjectName;
}


public Integer getStockAvailable(){
    return stockAvailable;
}


}