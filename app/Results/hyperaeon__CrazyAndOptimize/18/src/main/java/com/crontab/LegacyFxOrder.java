package com.crontab;
 import java.math.BigDecimal;
public class LegacyFxOrder {

 private  String ordrId;

 private  String productCategoryId;

 private  String baseCrncyId;

 private  String cntrCrncyId;

 private  String custId;

 private  BigDecimal baseCrncyAmt;

 private  BigDecimal cntrCrncyAmt;

 private  String isAggregate;

 private  String isBackToBack;

 private  String ordrTypeId;

 private  String logNum;


public void setProductCategoryId(String productCategoryId){
    this.productCategoryId = productCategoryId;
}


public String getIsBackToBack(){
    return isBackToBack;
}


public void setOrdrId(String ordrId){
    this.ordrId = ordrId;
}


public void setIsBackToBack(String isBackToBack){
    this.isBackToBack = isBackToBack;
}


public void setLogNum(String logNum){
    this.logNum = logNum;
}


public String getCustId(){
    return custId;
}


public void setBaseCrncyId(String baseCrncyId){
    this.baseCrncyId = baseCrncyId;
}


public void setCntrCrncyId(String cntrCrncyId){
    this.cntrCrncyId = cntrCrncyId;
}


public void setOrdrTypeId(String ordrTypeId){
    this.ordrTypeId = ordrTypeId;
}


public void setBaseCrncyAmt(BigDecimal baseCrncyAmt){
    this.baseCrncyAmt = baseCrncyAmt;
}


public void setCustId(String custId){
    this.custId = custId;
}


public BigDecimal getCntrCrncyAmt(){
    return cntrCrncyAmt;
}


public String getOrdrId(){
    return ordrId;
}


public String getCntrCrncyId(){
    return cntrCrncyId;
}


public BigDecimal getBaseCrncyAmt(){
    return baseCrncyAmt;
}


public String getLogNum(){
    return logNum;
}


public void setCntrCrncyAmt(BigDecimal cntrCrncyAmt){
    this.cntrCrncyAmt = cntrCrncyAmt;
}


public void setIsAggregate(String isAggregate){
    this.isAggregate = isAggregate;
}


public String getBaseCrncyId(){
    return baseCrncyId;
}


public String getIsAggregate(){
    return isAggregate;
}


public String getProductCategoryId(){
    return productCategoryId;
}


public String getOrdrTypeId(){
    return ordrTypeId;
}


}