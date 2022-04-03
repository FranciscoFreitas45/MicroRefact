package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_knowledge_publish")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgePublish {

 private  long serialVersionUID;

 private  String id;

 private  String knowid;

 private  String title;

 private  String summary;

 private  String content;

 private  String tags;

 private  String keyword;

 private  String dimenid;

 private  String dimentypeid;

 private  String organ;

 private  String knowledgetypeid;

 private  String knowbaseid;

 private  String pubstatus;

 private  boolean datastatus;

 private  int version;

 private  String knowledgetype;

 private  Date begintime;

 private  Date endtime;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  String auditor;

 private  String nlpnr;

 private  String nlpns;

 private  String nlpnt;

 private  String nlpnz;

 private  String keyphrase;

 private  Date updatetime;

 private  String attr;


public void setKeyphrase(String keyphrase){
    this.keyphrase = keyphrase;
}


public String getTags(){
    return tags;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public void setNlpnz(String nlpnz){
    this.nlpnz = nlpnz;
}


public String getDimentypeid(){
    return dimentypeid;
}


public String getTitle(){
    return title;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public void setKnowbaseid(String knowbaseid){
    this.knowbaseid = knowbaseid;
}


public void setNlpnr(String nlpnr){
    this.nlpnr = nlpnr;
}


public void setId(String id){
    this.id = id;
}


public void setNlpns(String nlpns){
    this.nlpns = nlpns;
}


public void setNlpnt(String nlpnt){
    this.nlpnt = nlpnt;
}


public String getAttr(){
    return attr;
}


public String getKeyphrase(){
    return keyphrase;
}


public void setKnowledgetypeid(String knowledgetypeid){
    this.knowledgetypeid = knowledgetypeid;
}


public void setVersion(int version){
    this.version = version;
}


public String getKnowbaseid(){
    return knowbaseid;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public String getKnowledgetypeid(){
    return knowledgetypeid;
}


public String getAuditor(){
    return auditor;
}


public String getKnowid(){
    return knowid;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setAttr(String attr){
    this.attr = attr;
}


public void setKnowledgetype(String knowledgetype){
    this.knowledgetype = knowledgetype;
}


public void setDimenid(String dimenid){
    this.dimenid = dimenid;
}


public String getPubstatus(){
    return pubstatus;
}


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public Date getBegintime(){
    return begintime;
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


public void setTags(String tags){
    this.tags = tags;
}


public void setKnowid(String knowid){
    this.knowid = knowid;
}


public String getDimenid(){
    return dimenid;
}


public Date getCreatetime(){
    return createtime;
}


public String getNlpnz(){
    return nlpnz;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getNlpnt(){
    return nlpnt;
}


public String getNlpnr(){
    return nlpnr;
}


public String getNlpns(){
    return nlpns;
}


public int getVersion(){
    return version;
}


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return summary;
}


public void setPubstatus(String pubstatus){
    this.pubstatus = pubstatus;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public void setAuditor(String auditor){
    this.auditor = auditor;
}


public void setBegintime(Date begintime){
    this.begintime = begintime;
}


public void setDimentypeid(String dimentypeid){
    this.dimentypeid = dimentypeid;
}


public String getKeyword(){
    return keyword;
}


public String getKnowledgetype(){
    return knowledgetype;
}


public String getOrgi(){
    return orgi;
}


public boolean isDatastatus(){
    return datastatus;
}


public Date getEndtime(){
    return endtime;
}


}