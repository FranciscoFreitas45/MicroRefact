package com.ipe.module.core.entity;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import javax.persistence;
import java.util.Date;
@JsonAutoDetect
@Entity
@Table(name = "t_sys_notice", schema = "", catalog = "db_work")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Notice extends IDEntity{

 private  String content;

 private  String appendixPath;

 private  String appendixName;

 private  Date createdDate;

 private  String userId;


public void setAppendixName(String appendixName){
    this.appendixName = appendixName;
}


public void setContent(String content){
    this.content = content;
}


@Column(name = "appendix_name")
public String getAppendixName(){
    return appendixName;
}


public void setCreatedDate(Date createdDate){
    this.createdDate = createdDate;
}


@Column(name = "content")
public String getContent(){
    return content;
}


public void setAppendixPath(String appendixPath){
    this.appendixPath = appendixPath;
}


@Column(name = "appendix_path")
public String getAppendixPath(){
    return appendixPath;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "created_date")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getCreatedDate(){
    return createdDate;
}


@Column(name = "user_id")
public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}