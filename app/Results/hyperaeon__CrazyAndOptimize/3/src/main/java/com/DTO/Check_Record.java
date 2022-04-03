package com.DTO;
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
public class Check_Record {

 private  Integer id;

 private  Integer reportId;

 private  Date query_date;

 private  String query_operator;

 private  String queryReason;

 private  Integer query_character;

 private  DateTime create_time;

 private  DateTime update_time;


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


public void setUpdate_time(DateTime update_time){
    this.update_time = update_time;
}


public void setQuery_date(Date query_date){
    this.query_date = query_date;
}


public DateTime getCreate_time(){
    return create_time;
}


public void setId(Integer id){
    this.id = id;
}


}