package com.ukefu.webim.web.model;
 import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_webim_monitor")
@org.hibernate.annotations.Proxy(lazy = false)
public class AgentReport {

 private  long serialVersionUID;

 private  String id;

 private  Date createtime;

 private  int agents;

 private  int readyagents;

 private  int incall;

 private  int users;

 private  int inquene;

 private  int busy;

 private  String orgi;

 private  String worktype;

 private  String workresult;

 private  String dataid;

 private  String datestr;

 private  String hourstr;

 private  String datehourstr;

 private  String type;


public int getReadyagents(){
    return readyagents;
}


public void setDatestr(String datestr){
    this.datestr = datestr;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setDatehourstr(String datehourstr){
    this.datehourstr = datehourstr;
}


public void setReadyagents(int readyagents){
    this.readyagents = readyagents;
}


public int getBusy(){
    return busy;
}


public void setUsers(int users){
    this.users = users;
}


public Date getCreatetime(){
    return createtime;
}


public int getUsers(){
    return users;
}


public String getDatehourstr(){
    return datehourstr;
}


public void setId(String id){
    this.id = id;
}


public String getDatestr(){
    return datestr;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setHourstr(String hourstr){
    this.hourstr = hourstr;
}


public void setInquene(int inquene){
    this.inquene = inquene;
}


public int getInquene(){
    return inquene;
}


public void setType(String type){
    this.type = type;
}


public void setIncall(int incall){
    this.incall = incall;
}


public String getHourstr(){
    return hourstr;
}


public void setBusy(int busy){
    this.busy = busy;
}


public void setWorkresult(String workresult){
    this.workresult = workresult;
}


public String getDataid(){
    return dataid;
}


public String getType(){
    return type;
}


public String getOrgi(){
    return orgi;
}


public void setWorktype(String worktype){
    this.worktype = worktype;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public int getIncall(){
    return incall;
}


public String getWorkresult(){
    return workresult;
}


public int getAgents(){
    return agents;
}


public void setAgents(int agents){
    this.agents = agents;
}


public String getWorktype(){
    return worktype;
}


}