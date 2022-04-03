package com.ukefu.webim.web.model;
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
@Entity
@Table(name = "uk_user")
@org.hibernate.annotations.Proxy(lazy = false)
public class User {

 private  long serialVersionUID;

@Id
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

public User() {
}public User(String id) {
    this.id = id;
}
public void setPassword(String password){
    this.password = password;
}


public void setLastname(String lastname){
    this.lastname = lastname;
}


public void setProvince(String province){
    this.province = province;
}


public String getDepartment(){
    return department;
}


public String getJobtitle(){
    return jobtitle;
}


public void setFans(int fans){
    this.fans = fans;
}


public String getUname(){
    return uname;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getStatus(){
    return status;
}


public void setGender(String gender){
    this.gender = gender;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public boolean isAgent(){
    return agent;
}


public void setId(String id){
    this.id = id;
}


public void setRoleList(List<Role> roleList){
    this.roleList = roleList;
}


public String getCity(){
    return city;
}


public void setOnline(boolean online){
    this.online = online;
}


@Transient
public CallCenterAgent getCcagent(){
    return ccagent;
}


public void setIntegral(int integral){
    this.integral = integral;
}


public void setAgentStatus(AgentStatus agentStatus){
    this.agentStatus = agentStatus;
}


public Date getLastlogintime(){
    return lastlogintime;
}


public void setCity(String city){
    this.city = city;
}


public String getOrgid(){
    return orgid;
}


public void setDepartment(String department){
    this.department = department;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getMidname(){
    return midname;
}


public void setFollows(int follows){
    this.follows = follows;
}


public String getGender(){
    return gender;
}


public void setRoleAuthMap(Map<String,Object> roleAuthMap){
    this.roleAuthMap = roleAuthMap;
}


public void setBirthday(String birthday){
    this.birthday = birthday;
}


public String getMemo(){
    return memo;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public void setEmail(String email){
    this.email = email;
}


public String getOrgan(){
    return organ;
}


public void setMidname(String midname){
    this.midname = midname;
}


public void setUsertype(String usertype){
    this.usertype = usertype;
}


public String getEmail(){
    return email;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setCallcenter(boolean callcenter){
    this.callcenter = callcenter;
}


public int getFollows(){
    return follows;
}


public void setSkill(String skill){
    this.skill = skill;
}


public void setOrdertype(String ordertype){
    this.ordertype = ordertype;
}


public void setLanguage(String language){
    this.language = language;
}


public String getSkill(){
    return skill;
}


public void setOrgid(String orgid){
    this.orgid = orgid;
}


public void setFirstname(String firstname){
    this.firstname = firstname;
}


public String getSecureconf(){
    return secureconf;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public int getFans(){
    return fans;
}


public boolean isSuperuser(){
    return superuser;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


@Transient
public boolean isOnline(){
    return online;
}


public void setJobtitle(String jobtitle){
    this.jobtitle = jobtitle;
}


public String getUsername(){
    return username;
}


@Transient
public boolean isLogin(){
    return login;
}


public boolean isDisabledesk(){
    return disabledesk;
}


public void setDisabledesk(boolean disabledesk){
    this.disabledesk = disabledesk;
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


public void setPassupdatetime(Date passupdatetime){
    this.passupdatetime = passupdatetime;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


@Transient
public String getSessionid(){
    return sessionid;
}


public void setCcagent(CallCenterAgent ccagent){
    this.ccagent = ccagent;
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


public void setUsername(String username){
    this.username = username;
}


public String getBirthday(){
    return birthday;
}


public Date getPassupdatetime(){
    return passupdatetime;
}


public void setSecureconf(String secureconf){
    this.secureconf = secureconf;
}


public void setStatus(String status){
    this.status = status;
}


public boolean isCallcenter(){
    return callcenter;
}


public void setAgent(boolean agent){
    this.agent = agent;
}


@Transient
public AgentStatus getAgentStatus(){
    return agentStatus;
}


public void setMaxuser(int maxuser){
    this.maxuser = maxuser;
}


public void setUname(String uname){
    this.uname = uname;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getPassword(){
    return password;
}


public String getNickname(){
    return nickname;
}


public void setLogin(boolean login){
    this.login = login;
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


public void setLastlogintime(Date lastlogintime){
    this.lastlogintime = lastlogintime;
}


public String getUsertype(){
    return usertype;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setSuperuser(boolean superuser){
    this.superuser = superuser;
}


public String getLastname(){
    return lastname;
}


}