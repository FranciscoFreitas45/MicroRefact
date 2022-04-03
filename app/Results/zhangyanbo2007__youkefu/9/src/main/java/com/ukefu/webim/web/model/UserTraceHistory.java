package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.util.event.UserEvent;
@Entity
@Table(name = "uk_userevent")
@Proxy(lazy = false)
public class UserTraceHistory implements UserEvent{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String username;

 private  String title;

 private  Date updatetime;

 private  String url;


public String getUrl(){
    return url;
}


public String getTitle(){
    return title;
}


public void setUsername(String username){
    this.username = username;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getOrgi(){
    return orgi;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setId(String id){
    this.id = id;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setTitle(String title){
    this.title = title;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setUrl(String url){
    this.url = url;
}


public String getUsername(){
    return username;
}


}