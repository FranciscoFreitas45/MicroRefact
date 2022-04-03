package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_templet")
@org.hibernate.annotations.Proxy(lazy = false)
public class Template {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String userid;

 private  String groupid;

 private  String description;

 private  String templettitle;

 private  String templettext;

 private  String templettype;

 private  Date createtime;

 private  String orgi;

 private  String iconstr;

 private  String memo;

 private  String typeid;

 private  int layoutcols;

 private  String datatype;

 private  String charttype;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setCharttype(String charttype){
    this.charttype = charttype;
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


public void setDescription(String description){
    this.description = description;
}


public void setTypeid(String typeid){
    this.typeid = typeid;
}


public String getDescription(){
    return description;
}


public String getDatatype(){
    return datatype;
}


@Transient
public String getTitle(){
    return this.groupid;
}


public Date getCreatetime(){
    return createtime;
}


public String getTemplettext(){
    return templettext;
}


public void setLayoutcols(int layoutcols){
    this.layoutcols = layoutcols;
}


public void setId(String id){
    this.id = id;
}


public String getIconstr(){
    return iconstr;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getTemplettitle(){
    return templettitle;
}


public String getCode(){
    return code;
}


public String getUserid(){
    return userid;
}


public String getCharttype(){
    return charttype;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getTemplettype(){
    return templettype;
}


public void setTemplettext(String templettext){
    this.templettext = templettext;
}


public void setDatatype(String datatype){
    this.datatype = datatype;
}


public void setGroupid(String groupid){
    this.groupid = groupid;
}


public void setCode(String code){
    this.code = code;
}


public String getTypeid(){
    return typeid;
}


public String getGroupid(){
    return groupid;
}


public void setTemplettype(String templettype){
    this.templettype = templettype;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public void setTemplettitle(String templettitle){
    this.templettitle = templettitle;
}


public int getLayoutcols(){
    return layoutcols;
}


public String getOrgi(){
    return orgi;
}


public void setIconstr(String iconstr){
    this.iconstr = iconstr;
}


}