package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_qc_template_item")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityTemplateItem {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  int maxscore;

 private  int minscore;

 private  String scheme;

 private  String templateid;

 private  String parentid;

 private  String type;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getScheme(){
    return scheme;
}


public void setMaxscore(int maxscore){
    this.maxscore = maxscore;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setScheme(String scheme){
    this.scheme = scheme;
}


public void setType(String type){
    this.type = type;
}


public String getTemplateid(){
    return templateid;
}


public Date getCreatetime(){
    return createtime;
}


public String getType(){
    return type;
}


public int getMinscore(){
    return minscore;
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


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public int getMaxscore(){
    return maxscore;
}


public void setMinscore(int minscore){
    this.minscore = minscore;
}


public String getParentid(){
    return parentid;
}


}