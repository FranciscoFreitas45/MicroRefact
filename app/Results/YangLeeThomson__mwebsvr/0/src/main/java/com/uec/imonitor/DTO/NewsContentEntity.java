package com.uec.imonitor.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
public class NewsContentEntity {

 private  String webpageCode;

 private  String content;

 private  Date createDatetime;

 private  String originalContent;


public Date getCreateDatetime(){
    return createDatetime;
}


public String getContent(){
    return content;
}


public String getOriginalContent(){
    return originalContent;
}


public String getWebpageCode(){
    return webpageCode;
}


}