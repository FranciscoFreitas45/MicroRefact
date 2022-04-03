package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_callcenter_media")
@org.hibernate.annotations.Proxy(lazy = false)
public class Media {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  Date createtime;

 private  Date updatetime;

 private  String filename;

 private  int filelength;

 private  String content;

 private  String hostid;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getContent(){
    return content;
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
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public void setFilename(String filename){
    this.filename = filename;
}


public int getFilelength(){
    return filelength;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public String getFilename(){
    return filename;
}


public Date getCreatetime(){
    return createtime;
}


public String getType(){
    return type;
}


public String getOrgi(){
    return orgi;
}


public String getHostid(){
    return hostid;
}


public void setFilelength(int filelength){
    this.filelength = filelength;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


}