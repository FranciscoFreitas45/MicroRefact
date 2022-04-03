package com.ipe.DTO;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
public class Remind extends IDEntity{

 private  String rtitle;

 private  String rcontent;

 private  String userId;

 private  String rtype;

 private  Date createdDate;

 private  String read;

 private  Date remindStDate;

 private  Date remindEnDate;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Column(name = "read_")
public String getRead(){
    return read;
}


@Column(name = "rcontent")
public String getRcontent(){
    return rcontent;
}


@Column(name = "rtype")
public String getRtype(){
    return rtype;
}


@Column(name = "remind_endate")
@JsonFormat(pattern = "yyyy-MM-dd")
public Date getRemindEnDate(){
    return remindEnDate;
}


@Column(name = "rtitle")
public String getRtitle(){
    return rtitle;
}


@Column(name = "created_date")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getCreatedDate(){
    return createdDate;
}


@Column(name = "user_id")
public String getUserId(){
    return userId;
}


@Column(name = "remind_stdate")
@JsonFormat(pattern = "yyyy-MM-dd")
public Date getRemindStDate(){
    return remindStDate;
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


}