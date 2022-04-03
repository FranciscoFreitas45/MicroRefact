package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_access")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmAccess {

 private  long serialVersionUID;

 private  String id;

 private  String knowledgeid;

 private  String knowledgeower;

 private  boolean datastatus;

 private  int version;

 private  Date createtime;

 private  String creater;

 private  String orgi;


public int getVersion(){
    return version;
}


public void setVersion(int version){
    this.version = version;
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


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getKnowledgeower(){
    return knowledgeower;
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


public boolean isDatastatus(){
    return datastatus;
}


}