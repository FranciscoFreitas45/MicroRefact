package com.jeecg.demo.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jform_order_ticket", schema = "")
@SuppressWarnings("serial")
public class JformOrderTicketEntity {

 private  java.lang.String id;

@Excel(name = "航班号", width = 15)
 private  java.lang.String ticketCode;

@Excel(name = "航班时间", width = 15, format = "yyyy-MM-dd")
 private  java.util.Date tickectDate;

 private  java.lang.String fckId;


public void setFckId(java.lang.String fckId){
    this.fckId = fckId;
}


public void setTicketCode(java.lang.String ticketCode){
    this.ticketCode = ticketCode;
}


@Column(name = "TICKECT_DATE", nullable = true, length = 10)
public java.util.Date getTickectDate(){
    return this.tickectDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "FCK_ID", nullable = false, length = 36)
public java.lang.String getFckId(){
    return this.fckId;
}


@Column(name = "TICKET_CODE", nullable = false, length = 100)
public java.lang.String getTicketCode(){
    return this.ticketCode;
}


public void setTickectDate(java.util.Date tickectDate){
    this.tickectDate = tickectDate;
}


}