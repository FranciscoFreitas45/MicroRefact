package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class CallOutTask implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String actid;

 private  String batid;

 private  String filterid;

 private  Date createtime;

 private  Date updatetime;

 private  String datastatus;

 private  String status;

 private  String exectype;

 private  int namenum;

 private  int execnum;

 private  int renum;

 private  int reorgannum;

 private  int assigned;

 private  int assignedorgan;

 private  int assignedai;

 private  int assignedforecast;

 private  int notassigned;

 private  String description;

 private  String name;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public int getNotassigned(){
    return notassigned;
}


public String getName(){
    return name;
}


public int getAssignedai(){
    return assignedai;
}


public void setReorgannum(int reorgannum){
    this.reorgannum = reorgannum;
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


public String getBatid(){
    return batid;
}


public int getAssigned(){
    return assigned;
}


public void setDescription(String description){
    this.description = description;
}


public String getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public int getAssignedorgan(){
    return assignedorgan;
}


public void setRenum(int renum){
    this.renum = renum;
}


public String getActid(){
    return actid;
}


public Date getCreatetime(){
    return createtime;
}


public int getNamenum(){
    return namenum;
}


public int getReorgannum(){
    return reorgannum;
}


public String getFilterid(){
    return filterid;
}


public void setActid(String actid){
    this.actid = actid;
}


public String getCreater(){
    return creater;
}


public void setAssignedorgan(int assignedorgan){
    this.assignedorgan = assignedorgan;
}


public String getExectype(){
    return exectype;
}


public String getDatastatus(){
    return datastatus;
}


public Date getUpdatetime(){
    return updatetime;
}


public int getAssignedforecast(){
    return assignedforecast;
}


public int getRenum(){
    return renum;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public int getExecnum(){
    return execnum;
}


public void setAssigned(int assigned){
    this.assigned = assigned;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssigned"))

.queryParam("assigned",assigned)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAssignedai(int assignedai){
    this.assignedai = assignedai;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssignedai"))

.queryParam("assignedai",assignedai)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAssignedforecast(int assignedforecast){
    this.assignedforecast = assignedforecast;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssignedforecast"))

.queryParam("assignedforecast",assignedforecast)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNotassigned(int notassigned){
    this.notassigned = notassigned;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNotassigned"))

.queryParam("notassigned",notassigned)
;
restTemplate.put(builder.toUriString(),null);
}


}