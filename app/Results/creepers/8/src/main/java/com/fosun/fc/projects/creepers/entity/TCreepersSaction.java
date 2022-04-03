package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_SACTION")
@NamedQuery(name = "TCreepersSaction.findAll", query = "SELECT t FROM TCreepersSaction t")
public class TCreepersSaction implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_SACTION_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_COURT_SACTION")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_SACTION_ID_GENERATOR")
 private  long id;

 private  String content;

 private  String memo;

@Column(name = "MODIFY_DT")
 private  String modifyDt;

 private  String name;

 private  String province;

 private  String type;

public TCreepersSaction() {
}
public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return this.name;
}


public void setProvince(String province){
    this.province = province;
}


public String getContent(){
    return this.content;
}


public long getId(){
    return this.id;
}


public String getModifyDt(){
    return this.modifyDt;
}


public void setType(String type){
    this.type = type;
}


public void setModifyDt(String modifyDt){
    this.modifyDt = modifyDt;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public String getType(){
    return this.type;
}


public String getProvince(){
    return this.province;
}


public void setId(long id){
    this.id = id;
}


}