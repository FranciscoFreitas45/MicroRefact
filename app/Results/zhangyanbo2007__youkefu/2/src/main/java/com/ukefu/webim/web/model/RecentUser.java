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
@Table(name = "uk_recentuser")
@org.hibernate.annotations.Proxy(lazy = false)
public class RecentUser {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  int newmsg;

 private  String lastmsg;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  User user;


public int getNewmsg(){
    return newmsg;
}


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


public String getLastmsg(){
    return lastmsg;
}


public void setNewmsg(int newmsg){
    this.newmsg = newmsg;
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


public void setLastmsg(String lastmsg){
    this.lastmsg = lastmsg;
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


}