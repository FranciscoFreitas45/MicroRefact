package DTO;
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

 private  int maxusers;

 private  int initmaxusers;

 private  boolean pulluser;

 private  String username;

 private  String name;

 private  Date updatetime;

 private  String workstatus;

 private  String userid;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getName(){
    return name;
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


public Date getLogindate(){
    return logindate;
}


public String getUserid(){
    return userid;
}


public String getAgentno(){
    return agentno;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getWorkstatus(){
    return workstatus;
}


public String getOrgi(){
    return orgi;
}


@Transient
public int getMaxusers(){
    SessionConfig sessionConfig = ServiceQuene.initSessionConfig(this.orgi);
    return sessionConfig != null ? sessionConfig.getMaxuser() : UKDataContext.AGENT_STATUS_MAX_USER;
}


public String getSkill(){
    return skill;
}


public void setSkill(String skill){
    this.skill = skill;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSkill"))

.queryParam("skill",skill)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSkillname(String skillname){
    this.skillname = skillname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSkillname"))

.queryParam("skillname",skillname)
;
restTemplate.put(builder.toUriString(),null);
}


}