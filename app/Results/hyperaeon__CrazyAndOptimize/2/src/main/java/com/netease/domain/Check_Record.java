package com.netease.domain;
 import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
@Entity(name = "Check_Record")
public class Check_Record {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  Integer id;

@Column(name = "report_id")
 private  Integer reportId;

@Column(name = "query_date")
@Type(type = "java.sql.Date")
 private  Date query_date;

@Column(name = "query_operator", length = 255)
 private  String query_operator;

@Column(name = "query_reason")
 private  String queryReason;

@Column(name = "query_character")
 private  Integer query_character;

@CreatedDate
@Column(name = "create_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime create_time;

@LastModifiedDate
@Column(name = "update_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime update_time;


public void setQuery_character(Integer query_character){
    this.query_character = query_character;
}


public String getQuery_operator(){
    return query_operator;
}


public Integer getReportId(){
    return reportId;
}


public DateTime getUpdate_time(){
    return update_time;
}


public Integer getQuery_character(){
    return query_character;
}


public Date getQuery_date(){
    return query_date;
}


public Integer getId(){
    return id;
}


public String getQueryReason(){
    return queryReason;
}


public void setQueryReason(String queryReason){
    this.queryReason = queryReason;
}


public void setUpdate_time(DateTime update_time){
    this.update_time = update_time;
}


public void setReportId(Integer reportId){
    this.reportId = reportId;
}


public void setQuery_date(Date query_date){
    this.query_date = query_date;
}


public DateTime getCreate_time(){
    return create_time;
}


public void setQuery_operator(String query_operator){
    this.query_operator = query_operator;
}


public void setId(Integer id){
    this.id = id;
}


public void setCreate_time(DateTime create_time){
    this.create_time = create_time;
}


}