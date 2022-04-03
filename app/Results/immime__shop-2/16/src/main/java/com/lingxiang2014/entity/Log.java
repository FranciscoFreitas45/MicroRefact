package com.lingxiang2014.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "lx_log")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_log_sequence")
public class Log extends BaseEntity{

 private  long serialVersionUID;

 public  String LOG_CONTENT_ATTRIBUTE_NAME;

 private  String operation;

 private  String operator;

 private  String content;

 private  String parameter;

 private  String ip;


public void setContent(String content){
    this.content = content;
}


@Column(nullable = false, updatable = false)
public String getIp(){
    return ip;
}


@Column(length = 3000, updatable = false)
public String getContent(){
    return content;
}


public void setParameter(String parameter){
    this.parameter = parameter;
}


@Column(nullable = false, updatable = false)
public String getOperation(){
    return operation;
}


public void setOperation(String operation){
    this.operation = operation;
}


public void setOperator(String operator){
    this.operator = operator;
}


public void setIp(String ip){
    this.ip = ip;
}


@Column(updatable = false)
public String getOperator(){
    return operator;
}


@Lob
@Column(updatable = false)
public String getParameter(){
    return parameter;
}


}