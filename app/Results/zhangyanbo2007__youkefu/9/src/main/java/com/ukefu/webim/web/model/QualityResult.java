package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_qc_result")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityResult {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String creater;

 private  String missionid;

 private  String dataid;

 private  String remarks;

 private  String adcom;

 private  String qacom;

 private  String imcom;

 private  Date createtime;

 private  String arithmetic;

 private  int score;

 private  int passscore;

 private  int totalscore;

 private  String qualityuser;

 private  String status;

 private  Date archivedate;

 private  String qualitytype;

 private  boolean isvp;

 private  boolean isadcom;

 private  boolean isqacom;

 private  boolean isimcom;

 private  boolean isrmk;

 private  boolean isitemrmk;

 private  boolean isitemdir;


public void setMissionid(String missionid){
    this.missionid = missionid;
}


public int getTotalscore(){
    return totalscore;
}


public String getQualitytype(){
    return qualitytype;
}


public boolean isIsvp(){
    return isvp;
}


public String getStatus(){
    return status;
}


public void setAdcom(String adcom){
    this.adcom = adcom;
}


public Date getArchivedate(){
    return archivedate;
}


public String getQualityuser(){
    return qualityuser;
}


public void setIsqacom(boolean isqacom){
    this.isqacom = isqacom;
}


public void setId(String id){
    this.id = id;
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
}


public void setIsadcom(boolean isadcom){
    this.isadcom = isadcom;
}


public void setArithmetic(String arithmetic){
    this.arithmetic = arithmetic;
}


public boolean isIsadcom(){
    return isadcom;
}


public void setIsvp(boolean isvp){
    this.isvp = isvp;
}


public boolean isIsitemrmk(){
    return isitemrmk;
}


public int getPassscore(){
    return passscore;
}


public String getQacom(){
    return qacom;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setTotalscore(int totalscore){
    this.totalscore = totalscore;
}


public void setImcom(String imcom){
    this.imcom = imcom;
}


public void setQacom(String qacom){
    this.qacom = qacom;
}


public boolean isIsitemdir(){
    return isitemdir;
}


public String getImcom(){
    return imcom;
}


public void setArchivedate(Date archivedate){
    this.archivedate = archivedate;
}


public String getArithmetic(){
    return arithmetic;
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


public void setIsrmk(boolean isrmk){
    this.isrmk = isrmk;
}


public Date getCreatetime(){
    return createtime;
}


public void setIsimcom(boolean isimcom){
    this.isimcom = isimcom;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getMissionid(){
    return missionid;
}


public void setQualityuser(String qualityuser){
    this.qualityuser = qualityuser;
}


public String getRemarks(){
    return remarks;
}


public String getAdcom(){
    return adcom;
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


public void setIsitemdir(boolean isitemdir){
    this.isitemdir = isitemdir;
}


public String getDataid(){
    return dataid;
}


public String getOrgi(){
    return orgi;
}


public boolean isIsrmk(){
    return isrmk;
}


public int getScore(){
    return score;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


public void setScore(int score){
    this.score = score;
}


public boolean isIsimcom(){
    return isimcom;
}


public boolean isIsqacom(){
    return isqacom;
}


}