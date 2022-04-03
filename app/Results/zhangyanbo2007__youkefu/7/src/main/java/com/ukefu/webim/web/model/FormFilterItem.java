package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_act_formfilter_item")
@org.hibernate.annotations.Proxy(lazy = false)
public class FormFilterItem {

 private  long serialVersionUID;

 private  String id;

 private  String formfilterid;

 private  String field;

 private  String cond;

 private  String value;

 private  String contype;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String itemtype;

 private  String comp;


public void setField(String field){
    this.field = field;
}


public String getContype(){
    return contype;
}


public String getCond(){
    return cond;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getField(){
    return field;
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
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setCond(String cond){
    this.cond = cond;
}


public void setContype(String contype){
    this.contype = contype;
}


public String getValue(){
    return value;
}


public void setFormfilterid(String formfilterid){
    this.formfilterid = formfilterid;
}


public String getFormfilterid(){
    return formfilterid;
}


public Date getCreatetime(){
    return createtime;
}


public void setValue(String value){
    this.value = value;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setComp(String comp){
    this.comp = comp;
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


public String getItemtype(){
    return itemtype;
}


public String getComp(){
    return comp;
}


public void setItemtype(String itemtype){
    this.itemtype = itemtype;
}


}