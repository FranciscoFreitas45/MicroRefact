package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_propertiesevent")
@org.hibernate.annotations.Proxy(lazy = false)
public class PropertiesEvent {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String tpid;

 private  String propertity;

 private  String field;

 private  String newvalue;

 private  String oldvalue;

 private  String textvalue;

 private  String orgi;

 private  String modifyid;

 private  String creater;

 private  Date createtime;

 private  String dataid;


public void setName(String name){
    this.name = name;
}


public void setField(String field){
    this.field = field;
}


public String getName(){
    return name;
}


public void setTextvalue(String textvalue){
    this.textvalue = textvalue;
}


public String getTpid(){
    return tpid;
}


public void setTpid(String tpid){
    this.tpid = tpid;
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


public void setOldvalue(String oldvalue){
    this.oldvalue = oldvalue;
}


public String getPropertity(){
    return propertity;
}


public String getOldvalue(){
    return oldvalue;
}


public Date getCreatetime(){
    return createtime;
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


public String getModifyid(){
    return modifyid;
}


public String getField(){
    return field;
}


public String getNewvalue(){
    return newvalue;
}


public String getDataid(){
    return dataid;
}


public void setNewvalue(String newvalue){
    this.newvalue = newvalue;
}


public void setModifyid(String modifyid){
    this.modifyid = modifyid;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setPropertity(String propertity){
    this.propertity = propertity;
}


public String getTextvalue(){
    return textvalue;
}


}