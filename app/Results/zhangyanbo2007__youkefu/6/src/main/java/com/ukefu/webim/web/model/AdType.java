package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ad_position")
@org.hibernate.annotations.Proxy(lazy = false)
public class AdType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  String area;

 private  Date updatetime;

 private  String parentid;

 private  String orgi;

 private  String imgurl;

 private  String tiptext;

 private  String url;

 private  String content;

 private  int weight;

 private  String adpos;

 private  String status;

 private  String adtype;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public int getWeight(){
    return weight;
}


public void setArea(String area){
    this.area = area;
}


public String getContent(){
    return content;
}


public String getAdtype(){
    return adtype;
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


public String getStatus(){
    return status;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public void setAdtype(String adtype){
    this.adtype = adtype;
}


public void setTiptext(String tiptext){
    this.tiptext = tiptext;
}


public void setId(String id){
    this.id = id;
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


public String getCode(){
    return code;
}


public String getParentid(){
    return parentid;
}


public void setUsername(String username){
    this.username = username;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setImgurl(String imgurl){
    this.imgurl = imgurl;
}


public void setWeight(int weight){
    this.weight = weight;
}


public void setStatus(String status){
    this.status = status;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public String getImgurl(){
    return imgurl;
}


public String getOrgi(){
    return orgi;
}


public String getAdpos(){
    return adpos;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getArea(){
    return area;
}


public void setAdpos(String adpos){
    this.adpos = adpos;
}


public String getTiptext(){
    return tiptext;
}


}