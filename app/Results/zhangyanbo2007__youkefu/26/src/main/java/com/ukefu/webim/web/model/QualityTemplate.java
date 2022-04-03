package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_qc_template")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityTemplate {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String organ;

 private  Date createtime;

 private  Date updatetime;

 private  String creater;

 private  String orgi;

 private  String arithmetic;

 private  String type;

 private  String status;

 private  int totalscore;

 private  int passscore;

 private  String remarks;

 private  boolean isvp;

 private  boolean isadcom;

 private  boolean isqacom;

 private  boolean isimcom;

 private  boolean isrmk;

 private  boolean isitemrmk;

 private  boolean isitemdir;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getArithmetic(){
    return arithmetic;
}


public int getTotalscore(){
    return totalscore;
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


public boolean isIsvp(){
    return isvp;
}


public String getStatus(){
    return status;
}


public void setIsrmk(boolean isrmk){
    this.isrmk = isrmk;
}


public Date getCreatetime(){
    return createtime;
}


public void setIsqacom(boolean isqacom){
    this.isqacom = isqacom;
}


public void setIsimcom(boolean isimcom){
    this.isimcom = isimcom;
}


public void setId(String id){
    this.id = id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public void setIsadcom(boolean isadcom){
    this.isadcom = isadcom;
}


public void setArithmetic(String arithmetic){
    this.arithmetic = arithmetic;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getRemarks(){
    return remarks;
}


public boolean isIsadcom(){
    return isadcom;
}


public void setType(String type){
    this.type = type;
}


public void setIsvp(boolean isvp){
    this.isvp = isvp;
}


public void setIsitemrmk(boolean isitemrmk){
    this.isitemrmk = isitemrmk;
}


public void setStatus(String status){
    this.status = status;
}


public void setPassscore(int passscore){
    this.passscore = passscore;
}


public boolean isIsitemrmk(){
    return isitemrmk;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public void setIsitemdir(boolean isitemdir){
    this.isitemdir = isitemdir;
}


public String getOrgan(){
    return organ;
}


public String getType(){
    return type;
}


public int getPassscore(){
    return passscore;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public boolean isIsrmk(){
    return isrmk;
}


public void setTotalscore(int totalscore){
    this.totalscore = totalscore;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


public boolean isIsimcom(){
    return isimcom;
}


public boolean isIsitemdir(){
    return isitemdir;
}


public boolean isIsqacom(){
    return isqacom;
}


}