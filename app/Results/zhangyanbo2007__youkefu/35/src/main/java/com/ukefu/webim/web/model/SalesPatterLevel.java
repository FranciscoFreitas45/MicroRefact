package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_spt_level")
@org.hibernate.annotations.Proxy(lazy = false)
public class SalesPatterLevel {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  int sortindex;

 private  String processid;

 private  Integer minscore;

 private  Integer maxscore;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public void setMaxscore(Integer maxscore){
    this.maxscore = maxscore;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setProcessid(String processid){
    this.processid = processid;
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


public Date getCreatetime(){
    return createtime;
}


public Integer getMinscore(){
    return minscore;
}


public String getProcessid(){
    return processid;
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


public void setMinscore(Integer minscore){
    this.minscore = minscore;
}


public Integer getMaxscore(){
    return maxscore;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public int getSortindex(){
    return sortindex;
}


}