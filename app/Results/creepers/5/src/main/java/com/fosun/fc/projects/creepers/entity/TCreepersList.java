package com.fosun.fc.projects.creepers.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_LIST")
@NamedQuery(name = "TCreepersList.findAll", query = "SELECT t FROM TCreepersList t")
public class TCreepersList {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_LIST_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_LIST")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_LIST_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(name = "MER_NAME", length = 500)
 private  String merName;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

 private  String memo;

public TCreepersList() {
}
public void setMemo(String memo){
    this.memo = memo;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getMemo(){
    return memo;
}


public String getMerName(){
    return this.merName;
}


public String getMerNo(){
    return this.merNo;
}


public void setId(long id){
    this.id = id;
}


public void setMerName(String merName){
    this.merName = merName;
}


public long getId(){
    return this.id;
}


}