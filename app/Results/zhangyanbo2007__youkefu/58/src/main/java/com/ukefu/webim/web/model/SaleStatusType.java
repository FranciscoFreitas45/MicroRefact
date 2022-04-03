package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_sales_status_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class SaleStatusType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String parentid;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String activityid;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
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


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setId(String id){
    this.id = id;
}


public void setParentid(String parentid){
    this.parentid = parentid;
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


public String getParentid(){
    return parentid;
}


}