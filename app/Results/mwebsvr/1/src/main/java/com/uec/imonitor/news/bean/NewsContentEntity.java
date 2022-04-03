package com.uec.imonitor.news.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "news_content")
public class NewsContentEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "webpage_code")
 private  String webpageCode;

@Column(name = "content")
 private  String content;

@Column(name = "create_datetime")
 private  Date createDatetime;

@Column(name = "original_content")
 private  String originalContent;


public void setContent(String content){
    this.content = content;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setWebpageCode(String webpageCode){
    this.webpageCode = webpageCode;
}


public String getContent(){
    return content;
}


public void setOriginalContent(String originalContent){
    this.originalContent = originalContent;
}


public String getOriginalContent(){
    return originalContent;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public String getWebpageCode(){
    return webpageCode;
}


}