package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.service.acd.ServiceQuene;
@Entity
@Table(name = "uk_agentstatus")
@org.hibernate.annotations.Proxy(lazy = false)
public class AgentStatus implements Comparable<AgentStatus>{

 private  long serialVersionUID;

 private  String id;

 private  String agentno;

 private  Date logindate;

 private  String status;

 private  String orgi;

 private  String agentserviceid;

 private  int serusernum;

 private  String skill;

 private  boolean busy;

 private  Date createtime;

 private  String skillname;

 private  int users;

@SuppressWarnings("unused")
 private  int maxusers;

@SuppressWarnings("unused")
 private  int initmaxusers;

 private  boolean pulluser;

 private  String username;

 private  String name;

 private  Date updatetime;

 private  String workstatus;

 private  String userid;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public void setWorkstatus(String workstatus){
    this.workstatus = workstatus;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getAgentserviceid(){
    return agentserviceid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


@Override
public int compareTo(AgentStatus o){
    int retValue = 0;
    SessionConfig sessionConfig = ServiceQuene.initSessionConfig(this.orgi);
    if (sessionConfig != null && !StringUtils.isBlank(sessionConfig.getDistribution()) && sessionConfig.getDistribution().equals("0")) {
        if (this.getUpdatetime() != null && o.getUpdatetime() != null) {
            retValue = (int) (this.getUpdatetime().getTime() - o.getUpdatetime().getTime());
        } else if (o.getUpdatetime() != null) {
            retValue = -1;
        } else {
            retValue = 1;
        }
    } else {
        retValue = this.users - o.users;
    }
    return retValue;
}


public String getStatus(){
    return status;
}


public int getSerusernum(){
    return serusernum;
}


public String getSkillname(){
    return skillname;
}


public String getUsername(){
    return username;
}


public void setPulluser(boolean pulluser){
    this.pulluser = pulluser;
}


public void setUsers(int users){
    this.users = users;
}


public Date getCreatetime(){
    return createtime;
}


@Transient
public int getInitmaxusers(){
    SessionConfig sessionConfig = ServiceQuene.initSessionConfig(this.orgi);
    return sessionConfig != null ? sessionConfig.getInitmaxuser() : getMaxusers();
}


public int getUsers(){
    return users;
}


public void setId(String id){
    this.id = id;
}


public Date getLogindate(){
    return logindate;
}


public void setLogindate(Date logindate){
    this.logindate = logindate;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setSerusernum(int serusernum){
    this.serusernum = serusernum;
}


public String getUserid(){
    return userid;
}


public String getAgentno(){
    return agentno;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setSkillname(String skillname){
    this.skillname = skillname;
}


public boolean isBusy(){
    return busy;
}


public void setUsername(String username){
    this.username = username;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getWorkstatus(){
    return workstatus;
}


public void setStatus(String status){
    this.status = status;
}


public void setInitmaxusers(int initmaxusers){
    this.initmaxusers = initmaxusers;
}


public boolean isPulluser(){
    return pulluser;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public void setBusy(boolean busy){
    this.busy = busy;
}


public String getOrgi(){
    return orgi;
}


@Transient
public int getMaxusers(){
    SessionConfig sessionConfig = ServiceQuene.initSessionConfig(this.orgi);
    return sessionConfig != null ? sessionConfig.getMaxuser() : UKDataContext.AGENT_STATUS_MAX_USER;
}


public void setSkill(String skill){
    this.skill = skill;
}


public void setMaxusers(int maxusers){
    this.maxusers = maxusers;
}


public String getSkill(){
    return skill;
}


}