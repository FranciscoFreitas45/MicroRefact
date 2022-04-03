package com.ukefu.webim.web.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_knowbase_config")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowbaseConfig {

 private  long serialVersionUID;

 private  String id;

 private  String knowbaseid;

 private  String basehost;

 private  String webname;

 private  String powerby;

 private  String keywords;

 private  String description;

 private  String beian;

 private  String footer;

 private  String indexlog;

 private  String hotwords;

 private  String banner;

 private  String creater;

 private  String orgi;

 private  String direction;

 private  String notfoundtip;


public void setBasehost(String basehost){
    this.basehost = basehost;
}


public void setPowerby(String powerby){
    this.powerby = powerby;
}


public String getDirection(){
    return direction;
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


public String getPowerby(){
    return powerby;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getBeian(){
    return beian;
}


public String getNotfoundtip(){
    return notfoundtip;
}


public void setKnowbaseid(String knowbaseid){
    this.knowbaseid = knowbaseid;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setDirection(String direction){
    this.direction = direction;
}


public void setNotfoundtip(String notfoundtip){
    this.notfoundtip = notfoundtip;
}


public String getFooter(){
    return footer;
}


public String getKnowbaseid(){
    return knowbaseid;
}


public String getIndexlog(){
    return indexlog;
}


public String getBasehost(){
    return basehost;
}


public String getKeywords(){
    return keywords;
}


public String getHotwords(){
    return hotwords;
}


public void setWebname(String webname){
    this.webname = webname;
}


public String getWebname(){
    return webname;
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


public void setHotwords(String hotwords){
    this.hotwords = hotwords;
}


public void setBeian(String beian){
    this.beian = beian;
}


public void setIndexlog(String indexlog){
    this.indexlog = indexlog;
}


public void setFooter(String footer){
    this.footer = footer;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getBanner(){
    return banner;
}


public void setBanner(String banner){
    this.banner = banner;
}


}