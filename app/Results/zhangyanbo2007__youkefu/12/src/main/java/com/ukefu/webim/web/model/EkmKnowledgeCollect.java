package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_ekm_kb_collect", createIndex = false)
@Entity
@Table(name = "uk_ekm_knowledge_collect")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgeCollect {

 private  long serialVersionUID;

 private  String id;

 private  int version;

 private  String status;

 private  String knowledgeower;

 private  String knowledgeid;

@Field(type = FieldType.String, store = true)
@Parent(type = "uk_ekm_kb_master")
 private  String kbid;

 private  EkmKnowledgeMaster knowledge;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String folderid;


public int getVersion(){
    return version;
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


public String getStatus(){
    return status;
}


public String getKnowledgeower(){
    return knowledgeower;
}


public void setStatus(String status){
    this.status = status;
}


public void setFolderid(String folderid){
    this.folderid = folderid;
}


public void setKnowledgeid(String knowledgeid){
    this.knowledgeid = knowledgeid;
}


public Date getCreatetime(){
    return createtime;
}


public void setKnowledgeower(String knowledgeower){
    this.knowledgeower = knowledgeower;
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


public void setCreater(String creater){
    this.creater = creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getKnowledgeid(){
    return knowledgeid;
}


public String getFolderid(){
    return folderid;
}


public void setKnowledge(EkmKnowledgeMaster knowledge){
    this.knowledge = knowledge;
}


}