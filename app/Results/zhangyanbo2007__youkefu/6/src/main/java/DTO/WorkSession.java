package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class WorkSession {

 private  long serialVersionUID;

 private  String id;

 private  String agent;

 private  String username;

 private  String agentno;

 private  boolean admin;

 private  String status;

 private  String code;

 private  String worktype;

 private  String orgi;

 private  String skill;

 private  String skillname;

 private  Date createtime;

 private  int duration;

 private  String sessionid;

 private  String clientid;

 private  String ipaddr;

 private  String hostname;

 private  String datestr;

 private  String userid;

 private  String organ;

 private  boolean firsttime;

 private  int firsttimes;

 private  Date begintime;

 private  Date endtime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public int getFirsttimes(){
    return firsttimes;
}


public Date getBegintime(){
    return begintime;
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


public String getSkillname(){
    return skillname;
}


public String getUsername(){
    return username;
}


public String getHostname(){
    return hostname;
}


public Date getCreatetime(){
    return createtime;
}


public int getDuration(){
    return duration;
}


public String getIpaddr(){
    return ipaddr;
}


public String getDatestr(){
    return datestr;
}


public String getSessionid(){
    return sessionid;
}


public String getCode(){
    return code;
}


public String getUserid(){
    return userid;
}


public String getAgentno(){
    return agentno;
}


public String getClientid(){
    return clientid;
}


public String getAgent(){
    return agent;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public String getWorktype(){
    return worktype;
}


public Date getEndtime(){
    return endtime;
}


public String getSkill(){
    return skill;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatetime"))

.queryParam("createtime",createtime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBegintime(Date begintime){
    this.begintime = begintime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBegintime"))

.queryParam("begintime",begintime)
;
restTemplate.put(builder.toUriString(),null);
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


public void setAdmin(boolean admin){
    this.admin = admin;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdmin"))

.queryParam("admin",admin)
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


public void setIpaddr(String ipaddr){
    this.ipaddr = ipaddr;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIpaddr"))

.queryParam("ipaddr",ipaddr)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHostname(String hostname){
    this.hostname = hostname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setHostname"))

.queryParam("hostname",hostname)
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


public void setClientid(String clientid){
    this.clientid = clientid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setClientid"))

.queryParam("clientid",clientid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSessionid"))

.queryParam("sessionid",sessionid)
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


public void setDatestr(String datestr){
    this.datestr = datestr;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDatestr"))

.queryParam("datestr",datestr)
;
restTemplate.put(builder.toUriString(),null);
}


}