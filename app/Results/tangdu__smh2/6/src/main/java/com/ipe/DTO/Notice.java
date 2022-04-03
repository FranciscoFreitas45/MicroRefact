package com.ipe.DTO;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import javax.persistence;
import java.util.Date;
public class Notice extends IDEntity{

 private  String content;

 private  String appendixPath;

 private  String appendixName;

 private  Date createdDate;

 private  String userId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Column(name = "appendix_name")
public String getAppendixName(){
    return appendixName;
}


@Column(name = "content")
public String getContent(){
    return content;
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


public void setAppendixName(String appendixName){
    this.appendixName = appendixName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAppendixName"))

.queryParam("appendixName",appendixName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(String userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatedDate(Date createdDate){
    this.createdDate = createdDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatedDate"))

.queryParam("createdDate",createdDate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAppendixPath(String appendixPath){
    this.appendixPath = appendixPath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAppendixPath"))

.queryParam("appendixPath",appendixPath)
;
restTemplate.put(builder.toUriString(),null);
}


}