package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class WorkMonitor {

 private  long serialVersionUID;

 private  String id;

 private  String agent;

 private  String username;

 private  String agentno;

 private  String extno;

 private  boolean admin;

 private  boolean firsttime;

 private  int firsttimes;

 private  String name;

 private  String status;

 private  String code;

 private  String worktype;

 private  String orgi;

 private  String agentserviceid;

 private  String skill;

 private  String skillname;

 private  boolean busy;

 private  String workstatus;

 private  Date createtime;

 private  String ani;

 private  String called;

 private  String direction;

 private  Date callstarttime;

 private  Date callendtime;

 private  int duration;

 private  String serviceid;

 private  String datestr;

 private  String servicestatus;

 private  String userid;

 private  String organ;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public String getServiceid(){
    return serviceid;
}


public String getName(){
    return name;
}


public String getExtno(){
    return extno;
}


public String getStatus(){
    return status;
}


public String getSkillname(){
    return skillname;
}


public int getDuration(){
    return duration;
}


public String getCode(){
    return code;
}


public Date getCallstarttime(){
    return callstarttime;
}


public String getAni(){
    return ani;
}


public String getOrgan(){
    return organ;
}


public String getWorktype(){
    return worktype;
}


public String getSkill(){
    return skill;
}


public int getFirsttimes(){
    return firsttimes;
}


public String getDirection(){
    return direction;
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


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getDatestr(){
    return datestr;
}


public String getUserid(){
    return userid;
}


public String getAgentno(){
    return agentno;
}


public String getCalled(){
    return called;
}


public String getWorkstatus(){
    return workstatus;
}


public String getAgent(){
    return agent;
}


public Date getCallendtime(){
    return callendtime;
}


public String getOrgi(){
    return orgi;
}


public String getServicestatus(){
    return servicestatus;
}


public void setAgent(String agent){
    this.agent = agent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAgent"))

.queryParam("agent",agent)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAgentno(String agentno){
    this.agentno = agentno;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAgentno"))

.queryParam("agentno",agentno)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdmin(boolean admin){
    this.admin = admin;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdmin"))

.queryParam("admin",admin)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUsername(String username){
    this.username = username;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUsername"))

.queryParam("username",username)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExtno(String extno){
    this.extno = extno;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExtno"))

.queryParam("extno",extno)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWorktype(String worktype){
    this.worktype = worktype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWorktype"))

.queryParam("worktype",worktype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDuration(int duration){
    this.duration = duration;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDuration"))

.queryParam("duration",duration)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBusy(boolean busy){
    this.busy = busy;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBusy"))

.queryParam("busy",busy)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFirsttime(boolean firsttime){
    this.firsttime = firsttime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFirsttime"))

.queryParam("firsttime",firsttime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFirsttimes(int firsttimes){
    this.firsttimes = firsttimes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFirsttimes"))

.queryParam("firsttimes",firsttimes)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatetime"))

.queryParam("createtime",createtime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDatestr(String datestr){
    this.datestr = datestr;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDatestr"))

.queryParam("datestr",datestr)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSkill(String skill){
    this.skill = skill;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSkill"))

.queryParam("skill",skill)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserid(String userid){
    this.userid = userid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserid"))

.queryParam("userid",userid)
;
restTemplate.put(builder.toUriString(),null);
}


}