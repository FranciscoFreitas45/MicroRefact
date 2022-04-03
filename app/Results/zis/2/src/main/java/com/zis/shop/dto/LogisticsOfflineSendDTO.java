package com.zis.shop.dto;
 import org.apache.commons.lang3.StringUtils;
public class LogisticsOfflineSendDTO {

 private  Long tid;

 private  String expressCompany;

 private  String expressNumber;

 private  String value;

 private  String display;


public Long getTid(){
    return tid;
}


public String getValue(){
    return value;
}


public String getExpressCompany(){
    return expressCompany;
}


public void setExpressNumber(String expressNumber){
    this.expressNumber = expressNumber;
}


public void setExpressCompany(String expressCompany){
    this.expressCompany = expressCompany;
}


public boolean isDefined(String value){
    if (StringUtils.isBlank(value)) {
        return false;
    }
    return getEnum(value) != null;
}


public String getExpressNumber(){
    return expressNumber;
}


public ExpressCompany getEnum(String value){
    for (ExpressCompany record : values()) {
        if (record.getValue().equals(value)) {
            return record;
        }
    }
    return null;
}


public String getDisplay(){
    return display;
}


public void setTid(Long tid){
    this.tid = tid;
}


}