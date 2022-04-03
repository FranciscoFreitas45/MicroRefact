package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class Organ {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String parent;

 private  Date createtime;

 private  String creater;

 private  boolean skill;

 private  String area;

 private  String username;

 private  Date updatetime;

 private  String orgi;

 private  String orgid;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public void setOrgid(String orgid){
    this.orgid = orgid;
}


public String getParent(){
    return parent;
}


public String getName(){
    return name;
}


public String getOrgid(){
    return orgid;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getCreater(){
    return creater;
}


public void setParent(String parent){
    this.parent = parent;
}


public String getCode(){
    return code;
}


public String getArea(){
    return area;
}


public boolean isSkill(){
    return skill;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSkill"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}