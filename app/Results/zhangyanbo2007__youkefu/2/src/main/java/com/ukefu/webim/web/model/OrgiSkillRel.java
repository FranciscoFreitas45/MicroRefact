package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_orgi_skill_rel")
@org.hibernate.annotations.Proxy(lazy = false)
public class OrgiSkillRel {

 private  long serialVersionUID;

 private  String id;

 private  String skillid;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;


public Date getCreatetime(){
    return createtime;
}


public void setSkillid(String skillid){
    this.skillid = skillid;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getOrgi(){
    return orgi;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
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


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getSkillid(){
    return skillid;
}


}