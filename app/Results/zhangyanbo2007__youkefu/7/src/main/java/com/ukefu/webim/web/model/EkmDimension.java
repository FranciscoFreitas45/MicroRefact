package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_dimension")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmDimension {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String organ;

 private  String orgi;

 private  boolean datastatus;

 private  String authorgan;

 private  int total;

 private  int viewnum;

 private  int collectnum;


public void setName(String name){
    this.name = name;
}


public void setTotal(int total){
    this.total = total;
}


public String getName(){
    return name;
}


public boolean getDatastatus(){
    return datastatus;
}


public int getCollectnum(){
    return collectnum;
}


public void setCollectnum(int collectnum){
    this.collectnum = collectnum;
}


public Date getUpdatetime(){
    return updatetime;
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
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public int getViewnum(){
    return viewnum;
}


public void setViewnum(int viewnum){
    this.viewnum = viewnum;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getAuthorgan(){
    return authorgan;
}


public void setAuthorgan(String authorgan){
    this.authorgan = authorgan;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgan(){
    return organ;
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


public int getTotal(){
    return total;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


}