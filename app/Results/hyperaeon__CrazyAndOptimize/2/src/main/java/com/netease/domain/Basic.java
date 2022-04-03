package com.netease.domain;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
@Entity(name = "Basic")
public class Basic {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  Integer id;

@Column(name = "report_number", nullable = false, length = 22)
 private  String reportNumber;

@Column(name = "query_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime query_time;

@Column(name = "report_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime reportTime;

@Column(name = "user_id")
 private  Integer userId;

@CreatedDate
@Column(name = "create_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime create_time;

@LastModifiedDate
@Column(name = "update_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime update_time;

@Column(name = "is_delete")
 private  Integer isDelete;


public DateTime getQuery_time(){
    return query_time;
}


public void setReportTime(DateTime reportTime){
    this.reportTime = reportTime;
}


public DateTime getUpdate_time(){
    return update_time;
}


public Integer getIsDelete(){
    return isDelete;
}


public DateTime getReportTime(){
    return reportTime;
}


public Integer getId(){
    return id;
}


public void setReportNumber(String reportNumber){
    this.reportNumber = reportNumber;
}


public void setUpdate_time(DateTime update_time){
    this.update_time = update_time;
}


public DateTime getCreate_time(){
    return create_time;
}


public void setIsDelete(Integer isDelete){
    this.isDelete = isDelete;
}


public void setId(Integer id){
    this.id = id;
}


public void setQuery_time(DateTime query_time){
    this.query_time = query_time;
}


@Override
public String toString(){
    return "Basic [id=" + id + ", report_number=" + reportNumber + ", query_time=" + query_time + ", reportTime=" + reportTime + ", userId=" + userId + ", create_time=" + create_time + ", update_time=" + update_time + ", isDelete=" + isDelete + "]";
}


public Integer getUserId(){
    return userId;
}


public void setCreate_time(DateTime create_time){
    this.create_time = create_time;
}


public String getReportNumber(){
    return reportNumber;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}