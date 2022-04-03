package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_publishedcube")
@org.hibernate.annotations.Proxy(lazy = false)
public class PublishedCube {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String modeltype;

 private  String dstype;

 private  String mposleft;

 private  String mpostop;

 private  String typeid;

 private  String orgi;

 private  String createdata;

 private  String dataid;

 private  String dataflag;

 private  int startindex;

 private  Date startdate;

 private  int dataversion;

 private  String creater;

 private  String userid;

 private  String username;

 private  String useremail;

 private  String cubecontent;

 private  String diclocation;

 private  Date createtime;

 private  Cube cube;


public void setName(String name){
    this.name = name;
}


public String getUseremail(){
    return useremail;
}


public void setDataversion(int dataversion){
    this.dataversion = dataversion;
}


public String getName(){
    return name;
}


public void setDiclocation(String diclocation){
    this.diclocation = diclocation;
}


public String getModeltype(){
    return modeltype;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getDataflag(){
    return dataflag;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setTypeid(String typeid){
    this.typeid = typeid;
}


public void setMposleft(String mposleft){
    this.mposleft = mposleft;
}


public String getUsername(){
    return username;
}


public Date getStartdate(){
    return startdate;
}


public void setStartdate(Date startdate){
    this.startdate = startdate;
}


public Date getCreatetime(){
    return createtime;
}


@Transient
public Cube getCube(){
    Base64 base64 = new Base64();
    try {
        return cube != null ? cube : (cube = (this.cubecontent == null ? null : (Cube) UKTools.toObject(base64.decode(this.cubecontent))));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return cube;
}


public void setModeltype(String modeltype){
    this.modeltype = modeltype;
}


public void setStartindex(int startindex){
    this.startindex = startindex;
}


public void setId(String id){
    this.id = id;
}


public void setMpostop(String mpostop){
    this.mpostop = mpostop;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCode(){
    return code;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setCubecontent(String cubecontent){
    this.cubecontent = cubecontent;
}


public void setCreatedata(String createdata){
    this.createdata = createdata;
}


public int getStartindex(){
    return startindex;
}


public void setUseremail(String useremail){
    this.useremail = useremail;
}


public String getMposleft(){
    return mposleft;
}


public void setUsername(String username){
    this.username = username;
}


public void setDataflag(String dataflag){
    this.dataflag = dataflag;
}


public void setCode(String code){
    this.code = code;
}


public String getTypeid(){
    return typeid;
}


public String getDiclocation(){
    return diclocation;
}


public int getDataversion(){
    return dataversion;
}


public String getDataid(){
    return dataid;
}


public String getCubecontent(){
    return cubecontent;
}


public void setDstype(String dstype){
    this.dstype = dstype;
}


public String getOrgi(){
    return orgi;
}


public String getCreatedata(){
    return createdata;
}


public String getDstype(){
    return dstype;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getMpostop(){
    return mpostop;
}


}