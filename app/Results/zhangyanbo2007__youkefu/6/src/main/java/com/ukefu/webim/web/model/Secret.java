package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_secret")
@Proxy(lazy = false)
public class Secret {

 private  long serialVersionUID;

 private  String id;

 private  String creater;

 private  Date createtime;

 private  boolean enable;

 private  String orgi;

 private  String password;

 private  String model;


public void setPassword(String password){
    this.password = password;
}


public String getModel(){
    return model;
}


public void setEnable(boolean enable){
    this.enable = enable;
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


public boolean isEnable(){
    return enable;
}


public String getPassword(){
    return password;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public void setModel(String model){
    this.model = model;
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