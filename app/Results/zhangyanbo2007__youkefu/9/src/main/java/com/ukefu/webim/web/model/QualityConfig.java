package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_qc_config")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityConfig {

 private  long serialVersionUID;

 private  String id;

 private  boolean phonetic;

 private  String engine;

 private  String appid;

 private  String secretkey;

 private  String lfasrhost;

 private  String filepiecesize;

 private  String storepath;

 private  int maxthreads;

 private  String creater;

 private  Date createtime;

 private  String updater;

 private  Date updatetime;

 private  String orgi;

 private  int archivetime;

 private  int aplarchivetime;

 private  boolean phonetrans;


public boolean isPhonetic(){
    return phonetic;
}


public void setSecretkey(String secretkey){
    this.secretkey = secretkey;
}


public String getFilepiecesize(){
    return filepiecesize;
}


public void setUpdater(String updater){
    this.updater = updater;
}


public String getStorepath(){
    return storepath;
}


public String getUpdater(){
    return updater;
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


public int getAplarchivetime(){
    return aplarchivetime;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getLfasrhost(){
    return lfasrhost;
}


public Date getCreatetime(){
    return createtime;
}


public void setArchivetime(int archivetime){
    this.archivetime = archivetime;
}


public String getEngine(){
    return engine;
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


public boolean isPhonetrans(){
    return phonetrans;
}


public void setEngine(String engine){
    this.engine = engine;
}


public String getSecretkey(){
    return secretkey;
}


public void setMaxthreads(int maxthreads){
    this.maxthreads = maxthreads;
}


public void setFilepiecesize(String filepiecesize){
    this.filepiecesize = filepiecesize;
}


public void setStorepath(String storepath){
    this.storepath = storepath;
}


public void setPhonetic(boolean phonetic){
    this.phonetic = phonetic;
}


public void setLfasrhost(String lfasrhost){
    this.lfasrhost = lfasrhost;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setPhonetrans(boolean phonetrans){
    this.phonetrans = phonetrans;
}


public int getArchivetime(){
    return archivetime;
}


public void setAplarchivetime(int aplarchivetime){
    this.aplarchivetime = aplarchivetime;
}


public String getAppid(){
    return appid;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public int getMaxthreads(){
    return maxthreads;
}


}