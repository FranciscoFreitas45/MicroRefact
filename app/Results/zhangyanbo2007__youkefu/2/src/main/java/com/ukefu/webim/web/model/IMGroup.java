package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_imgroup")
@org.hibernate.annotations.Proxy(lazy = false)
public class IMGroup {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String descript;

 private  String tipmessage;


public void setName(String name){
    this.name = name;
}


public void setTipmessage(String tipmessage){
    this.tipmessage = tipmessage;
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


public String getDescript(){
    return descript;
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


public String getTipmessage(){
    return tipmessage;
}


public void setDescript(String descript){
    this.descript = descript;
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