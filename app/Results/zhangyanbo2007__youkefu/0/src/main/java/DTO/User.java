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
public void setLastname(String lastname){
    this.lastname = lastname;
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


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public void setId(String id){
    this.id = id;
}


public String getCity(){
    return city;
}


@Transient
public CallCenterAgent getCcagent(){
    return ccagent;
}


public void setAgentStatus(AgentStatus agentStatus){
    this.agentStatus = agentStatus;
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


public void setBirthday(String birthday){
    this.birthday = birthday;
}


public String getMemo(){
    return memo;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getOrgan(){
    return organ;
}


public void setUsertype(String usertype){
    this.usertype = usertype;
}


public String getEmail(){
    return email;
}


public void setCreater(String creater){
    this.creater = creater;
}


public int getFollows(){
    return follows;
}


public void setOrdertype(String ordertype){
    this.ordertype = ordertype;
}


public String getSkill(){
    return skill;
}


public void setFirstname(String firstname){
    this.firstname = firstname;
}


public String getSecureconf(){
    return secureconf;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
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


public void setJobtitle(String jobtitle){
    this.jobtitle = jobtitle;
}


public String getUsername(){
    return username;
}


public boolean isDisabledesk(){
    return disabledesk;
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


public void setStatus(String status){
    this.status = status;
}


public void setAgent(boolean agent){
    this.agent = agent;
}


@Transient
public AgentStatus getAgentStatus(){
    return agentStatus;
}


public void setUname(String uname){
    this.uname = uname;
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


public void setSuperuser(boolean superuser){
    this.superuser = superuser;
}


public String getLastname(){
    return lastname;
}


public void setDisabledesk(boolean disabledesk){
    this.disabledesk = disabledesk;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDisabledesk"))

.queryParam("disabledesk",disabledesk)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmail(String email){
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmail"))

.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


public boolean isAgent(){
    return agent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/isAgent"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setPassword(String password){
    this.password = password;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPassword"))

.queryParam("password",password)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreatetime"))

.queryParam("createtime",createtime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUpdatetime"))

.queryParam("updatetime",updatetime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoleList(List<Role> roleList){
    this.roleList = roleList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoleList"))

.queryParam("roleList",roleList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoleAuthMap(Map<String,Object> roleAuthMap){
    this.roleAuthMap = roleAuthMap;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRoleAuthMap"))

.queryParam("roleAuthMap",roleAuthMap)
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


}