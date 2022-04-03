package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_tag")
@org.hibernate.annotations.Proxy(lazy = false)
public class Tag {

 private  String id;

 private  String orgi;

 private  Date createtime;

 private  int times;

 private  String creater;

 private  String tag;

 private  String icon;

 private  String color;

 private  String tagtype;


public int getTimes(){
    return times;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getColor(){
    return color;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setTagtype(String tagtype){
    this.tagtype = tagtype;
}


public String getTag(){
    return tag;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setColor(String color){
    this.color = color;
}


public Date getCreatetime(){
    return createtime;
}


public String getTagtype(){
    return tagtype;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setTag(String tag){
    this.tag = tag;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setTimes(int times){
    this.times = times;
}


public String getCreater(){
    return creater;
}


}