package com.crontab;
 import java.util.Date;
public class NbaResourceConfig {

 private  String host;

 private  String module;

 private  String key;

 private  String value;

 private  Date updateDate;

 private  String updateBy;


public String getModule(){
    return module;
}


public void setHost(String host){
    this.host = host;
}


public String getKey(){
    return key;
}


public String getValue(){
    return value;
}


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
}


public void setUpdateBy(String updateBy){
    this.updateBy = updateBy;
}


public void setValue(String value){
    this.value = value;
}


public Date getUpdateDate(){
    return updateDate;
}


public void setModule(String module){
    this.module = module;
}


public String getUpdateBy(){
    return updateBy;
}


public String getHost(){
    return host;
}


public void setKey(String key){
    this.key = key;
}


}