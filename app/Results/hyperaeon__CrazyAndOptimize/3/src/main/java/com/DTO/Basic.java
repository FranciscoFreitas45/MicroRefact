package com.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
public class Basic {

 private  Integer id;

 private  String reportNumber;

 private  DateTime query_time;

 private  DateTime reportTime;

 private  Integer userId;

 private  DateTime create_time;

 private  DateTime update_time;

 private  Integer isDelete;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public DateTime getQuery_time(){
    return query_time;
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


public DateTime getCreate_time(){
    return create_time;
}


public Integer getUserId(){
    return userId;
}


public String getReportNumber(){
    return reportNumber;
}


public void setReportNumber(String reportNumber){
    this.reportNumber = reportNumber;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setReportNumber"))

.queryParam("reportNumber",reportNumber)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreate_time(DateTime create_time){
    this.create_time = create_time;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreate_time"))

.queryParam("create_time",create_time)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpdate_time(DateTime update_time){
    this.update_time = update_time;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUpdate_time"))

.queryParam("update_time",update_time)
;
restTemplate.put(builder.toUriString(),null);
}


@Override
public String toString(){
    return "Basic [id=" + id + ", report_number=" + reportNumber + ", query_time=" + query_time + ", reportTime=" + reportTime + ", userId=" + userId + ", create_time=" + create_time + ", update_time=" + update_time + ", isDelete=" + isDelete + "]";
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/toString"))

;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


public void setIsDelete(Integer isDelete){
    this.isDelete = isDelete;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIsDelete"))

.queryParam("isDelete",isDelete)
;
restTemplate.put(builder.toUriString(),null);
}


}