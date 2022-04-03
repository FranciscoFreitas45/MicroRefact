package com.netease.domain;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name = "TB_OperationLog")
public class ApiLog implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

@Column(name = "app_id")
 private  String app_id;

@Column(name = "ip")
 private  String ip;

@Column(name = "module_id")
 private  String module_id;

@Column(name = "operator")
 private  String operator;

@Column(name = "operation")
 private  String operation;

@Column(name = "created_time")
 private  Date created_time;

@Column(name = "last_modify_time")
 private  Date last_modify_time;

@Column(name = "operate_time")
 private  Date operate_time;

@Column(name = "is_active")
 private  int is_active;

@Column(name = "consume_time")
 private  long consume_time;


public int getIs_active(){
    return is_active;
}


public void setIs_active(int is_active){
    this.is_active = is_active;
}


public Date getCreated_time(){
    return created_time;
}


public String getIp(){
    return ip;
}


public void setCreated_time(Date created_time){
    this.created_time = created_time;
}


public long getId(){
    return id;
}


public void setApp_id(String app_id){
    this.app_id = app_id;
}


public void setIp(String ip){
    this.ip = ip;
}


public void setConsume_time(long consume_time){
    this.consume_time = consume_time;
}


public void setLast_modify_time(Date last_modify_time){
    this.last_modify_time = last_modify_time;
}


public long getConsume_time(){
    return consume_time;
}


public String getModule_id(){
    return module_id;
}


public void setModule_id(String module_id){
    this.module_id = module_id;
}


public String getApp_id(){
    return app_id;
}


public Date getLast_modify_time(){
    return last_modify_time;
}


public Date getOperate_time(){
    return operate_time;
}


public void setOperate_time(Date operate_time){
    this.operate_time = operate_time;
}


public void setId(long id){
    this.id = id;
}


public String getOperation(){
    return operation;
}


public void setOperation(String operation){
    this.operation = operation;
}


public void setOperator(String operator){
    this.operator = operator;
}


@Override
public String toString(){
    return "ApiLog [id=" + id + ", app_id=" + app_id + ", ip=" + ip + ", module_id=" + module_id + ", operator=" + operator + ", operation=" + operation + ", created_time=" + created_time + ", last_modify_time=" + last_modify_time + ", operate_time=" + operate_time + ", is_active=" + is_active + ", consume_time=" + consume_time + "]";
}


public String getOperator(){
    return operator;
}


}