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
@Table(name = "aoa_user_login_record")
public class LoginRecord {

@Id
@Column(name = "record_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "ip_addr")
 private  String ipAddr;

@Column(name = "login_time")
 private  Date loginTime;

@Column(name = "session_id")
 private  String sessionId;

 private  String browser;

@Transient
 private  User user;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public LoginRecord(String ipAddr, Date loginTime, String browser, User user) {
    super();
    this.ipAddr = ipAddr;
    this.loginTime = loginTime;
    this.browser = browser;
    this.user = user;
}public LoginRecord() {
}
public String getIpAddr(){
    return ipAddr;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public Long getId(){
    return id;
}


public Date getLoginTime(){
    return loginTime;
}


public void setLoginTime(Date loginTime){
    this.loginTime = loginTime;
}


public void setSessionId(String sessionId){
    this.sessionId = sessionId;
}


public void setIpAddr(String ipAddr){
    this.ipAddr = ipAddr;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return "LoginRecord [id=" + id + ", ipAddr=" + ipAddr + ", loginTime=" + loginTime + ", sessionId=" + sessionId + ", browser=" + browser + "]";
}


public void setBrowser(String browser){
    this.browser = browser;
}


public String getSessionId(){
    return sessionId;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public String getBrowser(){
    return browser;
}


}