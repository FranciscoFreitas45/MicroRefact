package cn.gson.oasys.DTO;
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
public class UserLog {

 private  Long id;

 private  String ipAddr;

 private  String title;

 private  String url;

 private  Date logTime;

 private  User user;

public UserLog() {
}
public String getIpAddr(){
    return ipAddr;
}


public User getUser(){
    return user;
}


public Long getId(){
    return id;
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


public void setIpAddr(String ipAddr){
    this.ipAddr = ipAddr;
}


@Override
public String toString(){
    return "UserLog [id=" + id + ", ipAddr=" + ipAddr + ", title=" + title + ", url=" + url + ", logTime=" + logTime + "]";
}


}