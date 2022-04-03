package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_search_tag")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKwSearchTag {

 private  long serialVersionUID;

 private  String id;

 private  String tag;

 private  int times;

 private  Date updatetime;

 private  String orgi;


public int getTimes(){
    return times;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
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


public void setTag(String tag){
    this.tag = tag;
}


public void setTimes(int times){
    this.times = times;
}


public String getTag(){
    return tag;
}


}