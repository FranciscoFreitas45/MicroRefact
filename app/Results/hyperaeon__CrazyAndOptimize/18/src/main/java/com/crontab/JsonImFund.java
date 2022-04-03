package com.crontab;
 import java.math.BigDecimal;
public class JsonImFund {

 private  String im;

 private  String fund;

 private  BigDecimal custId;


public String getIm(){
    return im;
}


public void setIm(String im){
    this.im = im;
}


public void setCustId(BigDecimal custId){
    this.custId = custId;
}


public String getFund(){
    return fund;
}


public BigDecimal getCustId(){
    return custId;
}


public void setFund(String fund){
    this.fund = fund;
}


}