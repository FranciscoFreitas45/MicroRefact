package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_generation")
@org.hibernate.annotations.Proxy(lazy = false)
public class Generation {

 private  long serialVersionUID;

 private  String id;

 private  String model;

 private  int startinx;

 private  String orgi;

 private  String creater;

 private  Date createtime;


public int getStartinx(){
    return startinx;
}


public String getModel(){
    return model;
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


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setStartinx(int startinx){
    this.startinx = startinx;
}


}