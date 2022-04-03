package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_voice_transcription")
@org.hibernate.annotations.Proxy(lazy = false)
public class VoiceTranscription {

 private  long serialVersionUID;

 private  String id;

 private  String bg;

 private  String ed;

 private  String onebest;

 private  String speaker;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String organ;

 private  String callid;

 private  String recordfile;


public String getBg(){
    return bg;
}


public void setBg(String bg){
    this.bg = bg;
}


public void setEd(String ed){
    this.ed = ed;
}


public String getOnebest(){
    return onebest;
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


public void setOnebest(String onebest){
    this.onebest = onebest;
}


public String getEd(){
    return ed;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgan(){
    return organ;
}


public String getRecordfile(){
    return recordfile;
}


public void setRecordfile(String recordfile){
    this.recordfile = recordfile;
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


public void setSpeaker(String speaker){
    this.speaker = speaker;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getSpeaker(){
    return speaker;
}


public void setCallid(String callid){
    this.callid = callid;
}


public String getCallid(){
    return callid;
}


}