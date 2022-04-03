package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_favorites", createIndex = false)
@Entity
@Table(name = "uk_favorites")
@org.hibernate.annotations.Proxy(lazy = false)
public class Favorites {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String title;

 private  String model;

@Field(type = FieldType.String, store = true)
@Parent(type = "uk_workorders")
 private  String orderid;

 private  WorkOrders workOrders;

 private  Date createtime;

 private  Date updatetime;

 private  String creater;

 private  String username;

 private  String orgi;


public void setName(String name){
    this.name = name;
}


public void setWorkOrders(WorkOrders workOrders){
    this.workOrders = workOrders;
}


public String getModel(){
    return model;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
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


public void setTitle(String title){
    this.title = title;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getOrderid(){
    return orderid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
public String getId(){
    return id;
}


public void setOrderid(String orderid){
    this.orderid = orderid;
}


public String getUsername(){
    return username;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public void setModel(String model){
    this.model = model;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


@Transient
public WorkOrders getWorkOrders(){
    return workOrders;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getCode(){
    return code;
}


}