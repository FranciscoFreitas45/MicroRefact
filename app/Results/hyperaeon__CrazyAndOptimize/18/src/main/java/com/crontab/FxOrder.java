package com.crontab;
 import java.math.BigDecimal;
public class FxOrder {

 private  String ordrId;

 private  String pricingServiceId;

 private  String baseCrncyId;

 private  String cntrCrncyId;

 private  String custId;

 private  BigDecimal baseCrncyAmt;

 private  BigDecimal cntrCrncyAmt;


public void setOrdrId(String ordrId){
    this.ordrId = ordrId;
}


public void setPricingServiceId(String pricingServiceId){
    this.pricingServiceId = pricingServiceId;
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


public String getPricingServiceId(){
    return pricingServiceId;
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


public void setCntrCrncyAmt(BigDecimal cntrCrncyAmt){
    this.cntrCrncyAmt = cntrCrncyAmt;
}


public String getBaseCrncyId(){
    return baseCrncyId;
}


}