package com.fosun.fc.projects.creepers.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersTourBlackList implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String agentName;

 private  String decision;

 private  String docNo;

 private  String guideNo;

 private  String issue;

 private  String name;

 private  String period;

 private  String detailUrl;

 private  String type;

public TCreepersTourBlackList() {
}
public String getDecision(){
    return this.decision;
}


public String getName(){
    return this.name;
}


public long getId(){
    return this.id;
}


public String getGuideNo(){
    return this.guideNo;
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


public void setDecision(String decision){
    this.decision = decision;
}


public String getType(){
    return this.type;
}


public String getIssue(){
    return this.issue;
}


public String getDetailUrl(){
    return detailUrl;
}


public String getAgentName(){
    return this.agentName;
}


}