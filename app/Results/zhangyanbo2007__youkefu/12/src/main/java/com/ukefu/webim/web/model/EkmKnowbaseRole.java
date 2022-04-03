package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_knowbase_role")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowbaseRole {

 private  long serialVersionUID;

 private  String id;

 private  String knowbaseid;

 private  String roleid;

 private  Date createtime;

 private  String creater;

 private  String orgi;


public void setRoleid(String roleid){
    this.roleid = roleid;
}


public Date getCreatetime(){
    return createtime;
}


public String getKnowbaseid(){
    return knowbaseid;
}


public void setKnowbaseid(String knowbaseid){
    this.knowbaseid = knowbaseid;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public String getRoleid(){
    return roleid;
}


public void setCreater(String creater){
    this.creater = creater;
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


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


}