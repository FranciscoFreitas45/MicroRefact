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
public class Loan_Record_Detail {

 private  Integer id;

 private  Integer reportId;

 private  Integer accountType;

 private  Integer detailType;

 private  String detail_content;

 private  DateTime create_time;

 private  DateTime update_time;


public Integer getReportId(){
    return reportId;
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


public void setDetailType(Integer detailType){
    this.detailType = detailType;
}


public DateTime getCreate_time(){
    return create_time;
}


public String getDetail_content(){
    return detail_content;
}


public void setAccountType(Integer accountType){
    this.accountType = accountType;
}


public Integer getDetailType(){
    return detailType;
}


}