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
@Entity(name = "Loan_Record_Detail")
public class Loan_Record_Detail {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  Integer id;

@Column(name = "report_id")
 private  Integer reportId;

@Column(name = "account_type")
 private  Integer accountType;

@Column(name = "detail_type")
 private  Integer detailType;

@Column(name = "detail_content", length = 500)
 private  String detail_content;

@CreatedDate
@Column(name = "create_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime create_time;

@LastModifiedDate
@Column(name = "update_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime update_time;


public Integer getReportId(){
    return reportId;
}


public void setDetail_content(String detail_content){
    this.detail_content = detail_content;
}


public DateTime getUpdate_time(){
    return update_time;
}


public Integer getAccountType(){
    return accountType;
}


public Integer getId(){
    return id;
}


public void setUpdate_time(DateTime update_time){
    this.update_time = update_time;
}


public void setDetailType(Integer detailType){
    this.detailType = detailType;
}


public void setReportId(Integer reportId){
    this.reportId = reportId;
}


public DateTime getCreate_time(){
    return create_time;
}


public String getDetail_content(){
    return detail_content;
}


public void setId(Integer id){
    this.id = id;
}


public void setAccountType(Integer accountType){
    this.accountType = accountType;
}


public Integer getDetailType(){
    return detailType;
}


public void setCreate_time(DateTime create_time){
    this.create_time = create_time;
}


}