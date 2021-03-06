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
@Table(name = "T_CREEPERS_TOUR_BLACK_LIST")
@NamedQuery(name = "TCreepersTourBlackList.findAll", query = "SELECT t FROM TCreepersTourBlackList t")
public class TCreepersTourBlackList implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_TOUR_BLACK_LIST_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_TOUR_BLACK_LIST")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_TOUR_BLACK_LIST_ID_GENERATOR")
 private  long id;

@Column(name = "AGENT_NAME")
 private  String agentName;

 private  String decision;

@Column(name = "DOC_NO")
 private  String docNo;

@Column(name = "GUIDE_NO")
 private  String guideNo;

 private  String issue;

 private  String name;

 private  String period;

@Column(name = "DETAIL_URL")
 private  String detailUrl;

@Column(name = "TYPE")
 private  String type;

public TCreepersTourBlackList() {
}
public String getDecision(){
    return this.decision;
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return this.name;
}


public void setGuideNo(String guideNo){
    this.guideNo = guideNo;
}


public long getId(){
    return this.id;
}


public String getGuideNo(){
    return this.guideNo;
}


public void setPeriod(String period){
    this.period = period;
}


public void setType(String type){
    this.type = type;
}


public String getPeriod(){
    return this.period;
}


public String getDocNo(){
    return this.docNo;
}


public void setAgentName(String agentName){
    this.agentName = agentName;
}


public void setDecision(String decision){
    this.decision = decision;
}


public String getType(){
    return this.type;
}


public void setDocNo(String docNo){
    this.docNo = docNo;
}


public String getIssue(){
    return this.issue;
}


public void setId(long id){
    this.id = id;
}


public String getDetailUrl(){
    return detailUrl;
}


public void setDetailUrl(String detailUrl){
    this.detailUrl = detailUrl;
}


public String getAgentName(){
    return this.agentName;
}


public void setIssue(String issue){
    this.issue = issue;
}


}