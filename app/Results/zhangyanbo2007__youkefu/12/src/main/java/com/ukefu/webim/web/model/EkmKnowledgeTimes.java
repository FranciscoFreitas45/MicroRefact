package com.ukefu.webim.web.model;
 import javax.persistence.Transient;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;
import com.ukefu.util.UKTools;
import com.ukefu.util.task.ESData;
@Document(indexName = "uckefu", type = "uk_ekm_kb_times", createIndex = false)
public class EkmKnowledgeTimes implements ESData{

 private  long serialVersionUID;

 private  String id;

 private  int viewtimes;

 private  int commentstimes;

 private  int collectimes;

 private  int version;

 private  String knowledgeid;

@Field(type = FieldType.String, store = true)
@Parent(type = "uk_ekm_kb_master")
 private  String kbid;

 private  EkmKnowledgeMaster knowledge;

 private  Date createtime;

 private  String orgi;


public int getCommentstimes(){
    return commentstimes;
}


public int getVersion(){
    return version;
}


public void setCollectimes(int collectimes){
    this.collectimes = collectimes;
}


public void setCommentstimes(int commentstimes){
    this.commentstimes = commentstimes;
}


public int getViewtimes(){
    return viewtimes;
}


public void setVersion(int version){
    this.version = version;
}


@Transient
public EkmKnowledgeMaster getKnowledge(){
    return knowledge;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getKbid(){
    return kbid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
public String getId(){
    return id;
}


public int getCollectimes(){
    return collectimes;
}


public void setKnowledgeid(String knowledgeid){
    this.knowledgeid = knowledgeid;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public void setKbid(String kbid){
    this.kbid = kbid;
}


public void setId(String id){
    this.id = id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getKnowledgeid(){
    return knowledgeid;
}


public void setViewtimes(int viewtimes){
    this.viewtimes = viewtimes;
}


public void setKnowledge(EkmKnowledgeMaster knowledge){
    this.knowledge = knowledge;
}


}