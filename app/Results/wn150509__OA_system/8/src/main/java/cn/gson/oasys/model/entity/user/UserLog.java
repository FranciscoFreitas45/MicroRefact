package cn.gson.oasys.model.entity.user;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_user_log")
public class UserLog {

@Id
@Column(name = "log_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "ip_addr")
 private  String ipAddr;

 private  String title;

 private  String url;

@Column(name = "log_time")
 private  Date logTime;

@Transient
 private  User user;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public UserLog() {
}
public String getIpAddr(){
    return ipAddr;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public void setTitle(String title){
    this.title = title;
}


public Long getId(){
    return id;
}


public void setUrl(String url){
    this.url = url;
}


public Date getLogTime(){
    return logTime;
}


public String getUrl(){
    return url;
}


public String getTitle(){
    return title;
}


public void setLogTime(Date logTime){
    this.logTime = logTime;
}


public void setIpAddr(String ipAddr){
    this.ipAddr = ipAddr;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return "UserLog [id=" + id + ", ipAddr=" + ipAddr + ", title=" + title + ", url=" + url + ", logTime=" + logTime + "]";
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



}