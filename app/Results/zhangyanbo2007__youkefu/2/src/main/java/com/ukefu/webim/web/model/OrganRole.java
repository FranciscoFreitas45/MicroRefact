package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_organrole")
@org.hibernate.annotations.Proxy(lazy = false)
public class OrganRole {

 private  long serialVersionUID;

 private  String id;

 private  Organ organ;

 private  String dicid;

 private  String dicvalue;

 private  String creater;

 private  String orgi;

 private  Date createtime;


public void setDicvalue(String dicvalue){
    this.dicvalue = dicvalue;
}


public void setDicid(String dicid){
    this.dicid = dicid;
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


public void setOrgan(Organ organ){
    this.organ = organ;
}


public String getDicid(){
    return dicid;
}


public String getDicvalue(){
    return dicvalue;
}


@OneToOne(optional = true)
public Organ getOrgan(){
    return organ;
}


public Date getCreatetime(){
    return createtime;
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