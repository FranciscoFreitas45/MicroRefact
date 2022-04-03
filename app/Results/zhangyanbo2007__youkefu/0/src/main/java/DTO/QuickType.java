package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class QuickType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  int inx;

 private  String quicktype;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  Date startdate;

 private  Date enddate;

 private  boolean enable;

 private  String description;

 private  Date updatetime;

 private  String parentid;

 private  String orgi;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getName(){
    return name;
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


public String getDescription(){
    return description;
}


public String getUsername(){
    return username;
}


public Date getStartdate(){
    return startdate;
}


public Date getCreatetime(){
    return createtime;
}


public Date getEnddate(){
    return enddate;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public String getCreater(){
    return creater;
}


public String getCode(){
    return code;
}


public String getParentid(){
    return parentid;
}


public void setEnable(boolean enable){
    this.enable = enable;
}


public Date getUpdatetime(){
    return updatetime;
}


public int getInx(){
    return inx;
}


public String getQuicktype(){
    return quicktype;
}


public String getOrgi(){
    return orgi;
}


public void setQuicktype(String quicktype){
    this.quicktype = quicktype;
}


public void setCreater(String creater){
    this.creater = creater;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreater"))

.queryParam("creater",creater)
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


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInx(int inx){
    this.inx = inx;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInx"))

.queryParam("inx",inx)
;
restTemplate.put(builder.toUriString(),null);
}


}