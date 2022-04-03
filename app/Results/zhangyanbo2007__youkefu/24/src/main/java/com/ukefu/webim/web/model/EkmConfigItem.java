package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_configitem")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmConfigItem {

 private  long serialVersionUID;

 private  String id;

 private  String upfilesize;

 private  Date createtime;

 private  String orgi;


public Date getCreatetime(){
    return createtime;
}


public String getUpfilesize(){
    return upfilesize;
}


public void setUpfilesize(String upfilesize){
    this.upfilesize = upfilesize;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
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


}