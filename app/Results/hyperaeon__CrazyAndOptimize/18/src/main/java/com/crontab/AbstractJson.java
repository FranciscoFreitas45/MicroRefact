package com.crontab;
 import com.test.CurrencyPairGroup;
public class AbstractJson {

 protected  CurrencyPairGroup currencyPairGroup;

 protected  String lastUpdatedById;

 protected  Long lastUpdatedDttm;


public String getLastUpdatedById(){
    return lastUpdatedById;
}


public void setCurrencyPairGroup(CurrencyPairGroup currencyPairGroup){
    this.currencyPairGroup = currencyPairGroup;
}


public void setLastUpdatedDttm(Long lastUpdatedDttm){
    this.lastUpdatedDttm = lastUpdatedDttm;
}


public Long getLastUpdatedDttm(){
    return lastUpdatedDttm;
}


public void setLastUpdatedById(String lastUpdatedById){
    this.lastUpdatedById = lastUpdatedById;
}


public CurrencyPairGroup getCurrencyPairGroup(){
    return currencyPairGroup;
}


}