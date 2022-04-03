package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_noticetarget")
@org.hibernate.annotations.Proxy(lazy = false)
public class NoticeTarget {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  String targettype;

 private  String target;

 private  String noticeid;

 private  boolean checked;


public void setName(String name){
    this.name = name;
}


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


public boolean isChecked(){
    return checked;
}


public String getTargettype(){
    return targettype;
}


public String getTarget(){
    return target;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public void setChecked(boolean checked){
    this.checked = checked;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setTargettype(String targettype){
    this.targettype = targettype;
}


public void setTarget(String target){
    this.target = target;
}


public void setNoticeid(String noticeid){
    this.noticeid = noticeid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getNoticeid(){
    return noticeid;
}


}