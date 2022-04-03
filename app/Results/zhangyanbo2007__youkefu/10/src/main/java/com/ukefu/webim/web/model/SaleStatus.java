package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_sales_status")
@org.hibernate.annotations.Proxy(lazy = false)
public class SaleStatus {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String cate;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String memo;

 private  String activityid;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setActivityid(String activityid){
    this.activityid = activityid;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public Date getCreatetime(){
    return createtime;
}


public String getCate(){
    return cate;
}


public String getOrgi(){
    return orgi;
}


public void setCate(String cate){
    this.cate = cate;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getActivityid(){
    return activityid;
}


public String getCode(){
    return code;
}


}