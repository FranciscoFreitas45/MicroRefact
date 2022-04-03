package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_cubemetadata")
@org.hibernate.annotations.Proxy(lazy = false)
public class CubeMetadata {

 private  long serialVersionUID;

 public  String id;

 private  MetadataTable tb;

 private  String orgi;

 private  String name;

 private  String code;

 private  String title;

 private  Date createtime;

 private  String creater;

 private  String cubeid;

 private  String postop;

 private  String posleft;

 private  String mtype;

 private  String parameters;

 private  String attribue;

 private  String namealias;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setNamealias(String namealias){
    this.namealias = namealias;
}


public void setMtype(String mtype){
    this.mtype = mtype;
}


public void setCubeid(String cubeid){
    this.cubeid = cubeid;
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


public void setParameters(String parameters){
    this.parameters = parameters;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getPosleft(){
    return posleft;
}


public String getCode(){
    return code;
}


public String getCubeid(){
    return cubeid;
}


public String getMtype(){
    return mtype;
}


public void setCode(String code){
    this.code = code;
}


public void setTitle(String title){
    this.title = title;
}


public void setPosleft(String posleft){
    this.posleft = posleft;
}


public void setTb(MetadataTable tb){
    this.tb = tb;
}


public String getOrgi(){
    return orgi;
}


public String getParameters(){
    return parameters;
}


public String getAttribue(){
    return attribue;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setAttribue(String attribue){
    this.attribue = attribue;
}


public void setPostop(String postop){
    this.postop = postop;
}


public String getNamealias(){
    return namealias;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "tb")
public MetadataTable getTb(){
    return tb;
}


public String getPostop(){
    return postop;
}


}