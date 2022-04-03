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
@Table(name = "T_CREEPERS_MER_KEYMAN")
@NamedQuery(name = "TCreepersMerKeyman.findAll", query = "SELECT t FROM TCreepersMerKeyman t")
public class TCreepersMerKeyman {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_MER_KEYMAN_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_MER_KEYMAN")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_MER_KEYMAN_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(length = 50)
 private  String name;

@Column(name = "POSITION", length = 50)
 private  String position;

@Column(name = "SEQ_NO", length = 20)
 private  String seqNo;

public TCreepersMerKeyman() {
}
public void setName(String name){
    this.name = name;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getName(){
    return this.name;
}


public String getPosition(){
    return this.position;
}


public String getMerNo(){
    return this.merNo;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return this.id;
}


public String getSeqNo(){
    return this.seqNo;
}


public void setPosition(String position){
    this.position = position;
}


}