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
@Entity(name = "Loan_Record")
public class Loan_Record {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  Integer id;

@Column(name = "report_id")
 private  Integer reportId;

@Column(name = "account_amount")
 private  Integer account_amount;

@Column(name = "unpayed_account")
 private  Integer unpayed_account;

@Column(name = "overdue_account")
 private  Integer overdue_account;

@Column(name = "overdue_ninty_account")
 private  Integer overdue_ninty_account;

@Column(name = "warrant_amount")
 private  Integer warrant_amount;

@Column(name = "account_type")
 private  Integer account_type;

@CreatedDate
@Column(name = "create_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime create_time;

@LastModifiedDate
@Column(name = "update_time")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
 private  DateTime update_time;


public void setOverdue_account(Integer overdue_account){
    this.overdue_account = overdue_account;
}


public Integer getAccount_type(){
    return account_type;
}


public Integer getReportId(){
    return reportId;
}


public void setUnpayed_account(Integer unpayed_account){
    this.unpayed_account = unpayed_account;
}


public DateTime getUpdate_time(){
    return update_time;
}


public void setAccount_amount(Integer account_amount){
    this.account_amount = account_amount;
}


public Integer getWarrant_amount(){
    return warrant_amount;
}


public void setAccount_type(Integer account_type){
    this.account_type = account_type;
}


public Integer getId(){
    return id;
}


public Integer getOverdue_ninty_account(){
    return overdue_ninty_account;
}


public void setOverdue_ninty_account(Integer overdue_ninty_account){
    this.overdue_ninty_account = overdue_ninty_account;
}


public void setUpdate_time(DateTime update_time){
    this.update_time = update_time;
}


public Integer getOverdue_account(){
    return overdue_account;
}


public void setReportId(Integer reportId){
    this.reportId = reportId;
}


public Integer getUnpayed_account(){
    return unpayed_account;
}


public DateTime getCreate_time(){
    return create_time;
}


public void setWarrant_amount(Integer warrant_amount){
    this.warrant_amount = warrant_amount;
}


public Integer getAccount_amount(){
    return account_amount;
}


public void setId(Integer id){
    this.id = id;
}


public void setCreate_time(DateTime create_time){
    this.create_time = create_time;
}


}