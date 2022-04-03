package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
public class User {

 private  long serialVersionUID;

 private  String id;

 private  String sessionid;

 private  String username;

 private  String password;

 private  String email;

 private  String uname;

 private  String firstname;

 private  String midname;

 private  String lastname;

 private  String language;

 private  String jobtitle;

 private  String department;

 private  String gender;

 private  String mobile;

 private  String birthday;

 private  String nickname;

 private  String secureconf;

 private  String usertype;

 private  boolean superuser;

 private  String orgi;

 private  String orgid;

 private  String creater;

 private  Date createtime;

 private  Date passupdatetime;

 private  Date updatetime;

 private  String memo;

 private  String organ;

 private  boolean agent;

 private  boolean callcenter;

 private  String skill;

 private  String city;

 private  String province;

 private  boolean login;

 private  boolean online;

 private  String status;

 private  boolean datastatus;

 private  int maxuser;

 private  String ordertype;

 private  boolean disabledesk;

 private  Date lastlogintime;

 private  CallCenterAgent ccagent;

 private  AgentStatus agentStatus;

 private  int fans;

 private  int follows;

 private  int integral;

 private  List<Role> roleList;

 private  Map<String,Object> roleAuthMap;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

public User() {
}public User(String id) {
    this.id = id;
}
public String getDepartment(){
    return department;
}


public String getJobtitle(){
    return jobtitle;
}


public String getUname(){
    return uname;
}


public String getStatus(){
    return status;
}


public String getCity(){
    return city;
}


@Transient
public CallCenterAgent getCcagent(){
    return ccagent;
}


public Date getLastlogintime(){
    return lastlogintime;
}


public String getOrgid(){
    return orgid;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getMidname(){
    return midname;
}


public String getGender(){
    return gender;
}


public String getMemo(){
    return memo;
}


public String getOrgan(){
    return organ;
}


public String getEmail(){
    return email;
}


public int getFollows(){
    return follows;
}


public String getSkill(){
    return skill;
}


public String getSecureconf(){
    return secureconf;
}


public int getFans(){
    return fans;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public int getMaxuser(){
    return maxuser;
}


public String getProvince(){
    return province;
}


@Transient
public List<Role> getRoleList(){
    return roleList;
}


public String getCreater(){
    return creater;
}


@Transient
public String getSessionid(){
    return sessionid;
}


public String getLanguage(){
    return language;
}


@Transient
public Map<String,Object> getRoleAuthMap(){
    return roleAuthMap;
}


public String getFirstname(){
    return firstname;
}


public String getBirthday(){
    return birthday;
}


public Date getPassupdatetime(){
    return passupdatetime;
}


@Transient
public AgentStatus getAgentStatus(){
    return agentStatus;
}


public String getPassword(){
    return password;
}


public String getNickname(){
    return nickname;
}


public int getIntegral(){
    return integral;
}


public String getOrdertype(){
    return ordertype;
}


public String getOrgi(){
    return orgi;
}


public String getMobile(){
    return mobile;
}


public String getUsertype(){
    return usertype;
}


public String getLastname(){
    return lastname;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSessionid"))

.queryParam("sessionid",sessionid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setId(String id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUsername(String username){
    this.username = username;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUsername"))

.queryParam("username",username)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


}