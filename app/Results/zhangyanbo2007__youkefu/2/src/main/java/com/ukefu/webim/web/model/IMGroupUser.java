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
@Table(name = "uk_imgroup_user")
@org.hibernate.annotations.Proxy(lazy = false)
public class IMGroupUser {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  boolean admin;

 private  User user;

 private  IMGroup imgroup;


public Date getUpdatetime(){
    return updatetime;
}


@OneToOne
public User getUser(){
    return user;
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


public boolean isAdmin(){
    return admin;
}


public Date getCreatetime(){
    return createtime;
}


public void setImgroup(IMGroup imgroup){
    this.imgroup = imgroup;
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


@OneToOne
public IMGroup getImgroup(){
    return imgroup;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setUser(User user){
    this.user = user;
}


public void setAdmin(boolean admin){
    this.admin = admin;
}


}