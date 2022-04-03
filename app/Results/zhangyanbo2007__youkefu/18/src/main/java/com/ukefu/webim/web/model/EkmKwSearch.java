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
@Table(name = "uk_ekm_search")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKwSearch {

 private  long serialVersionUID;

 private  String id;

 private  String conditions;

 private  String creater;

 private  String orgi;

 private  String organ;

 private  Date createtime;

 private  String type;

 private  boolean badword;


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


public String getConditions(){
    return conditions;
}


public void setType(String type){
    this.type = type;
}


public void setBadword(boolean badword){
    this.badword = badword;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public void setConditions(String conditions){
    this.conditions = conditions;
}


public String getOrgan(){
    return organ;
}


public Date getCreatetime(){
    return createtime;
}


public String getType(){
    return type;
}


public boolean isBadword(){
    return badword;
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


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


}