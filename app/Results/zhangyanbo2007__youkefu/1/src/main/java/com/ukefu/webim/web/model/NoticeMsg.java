package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_noticemsg")
@org.hibernate.annotations.Proxy(lazy = false)
public class NoticeMsg {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  Date createtime;

 private  String creater;

 private  String createrusername;

 private  String orgi;

 private  String target;

 private  String title;

 private  String content;

 private  String summary;

 private  String tags;

 private  String keyword;

 private  boolean status;

 private  boolean datastatus;

 private  String type;

 private  String terracetype;

 private  String jarurl;

 private  boolean confirm;

 private  Date invalidtime;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public boolean isStatus(){
    return status;
}


public String getContent(){
    return content;
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


public String getTags(){
    return tags;
}


public void setTags(String tags){
    this.tags = tags;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public void setJarurl(String jarurl){
    this.jarurl = jarurl;
}


public void setConfirm(boolean confirm){
    this.confirm = confirm;
}


public String getTarget(){
    return target;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public String getTerracetype(){
    return terracetype;
}


public boolean isConfirm(){
    return confirm;
}


public void setId(String id){
    this.id = id;
}


public void setTarget(String target){
    this.target = target;
}


public Date getInvalidtime(){
    return invalidtime;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public void setCreaterusername(String createrusername){
    this.createrusername = createrusername;
}


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return summary;
}


public String getCreaterusername(){
    return createrusername;
}


public void setTitle(String title){
    this.title = title;
}


public void setType(String type){
    this.type = type;
}


public void setTerracetype(String terracetype){
    this.terracetype = terracetype;
}


public void setStatus(boolean status){
    this.status = status;
}


public String getJarurl(){
    return jarurl;
}


public String getKeyword(){
    return keyword;
}


public String getType(){
    return type;
}


public String getOrgi(){
    return orgi;
}


public void setInvalidtime(Date invalidtime){
    this.invalidtime = invalidtime;
}


public void setCreater(String creater){
    this.creater = creater;
}


public boolean isDatastatus(){
    return datastatus;
}


}