package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_act_role")
@org.hibernate.annotations.Proxy(lazy = false)
public class CallOutRole {

 private  long serialVersionUID;

 private  String id;

 private  String rolename;

 private  String roleid;

 private  String bustype;

 private  String organid;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;


public String getBustype(){
    return bustype;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public String getRoleid(){
    return roleid;
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


public void setBustype(String bustype){
    this.bustype = bustype;
}


public void setRoleid(String roleid){
    this.roleid = roleid;
}


public String getOrganid(){
    return organid;
}


public Date getCreatetime(){
    return createtime;
}


public String getRolename(){
    return rolename;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setRolename(String rolename){
    this.rolename = rolename;
}


public void setOrganid(String organid){
    this.organid = organid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


}