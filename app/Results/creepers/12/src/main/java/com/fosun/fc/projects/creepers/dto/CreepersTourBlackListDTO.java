package com.fosun.fc.projects.creepers.dto;
 public class CreepersTourBlackListDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String type;

 private  String docNo;

 private  String guideNo;

 private  String agentName;

 private  String name;

 private  String issue;

 private  String decision;

 private  String period;

 private  String detailUrl;


public void setName(String name){
    this.name = name;
}


public String getDecision(){
    return decision;
}


public String getName(){
    return name;
}


public void setGuideNo(String guideNo){
    this.guideNo = guideNo;
}


public String getGuideNo(){
    return guideNo;
}


public void setType(String type){
    this.type = type;
}


public void setPeriod(String period){
    this.period = period;
}


public String getPeriod(){
    return period;
}


public String getDocNo(){
    return docNo;
}


public void setAgentName(String agentName){
    this.agentName = agentName;
}


public void setDecision(String decision){
    this.decision = decision;
}


public String getType(){
    return type;
}


public void setDocNo(String docNo){
    this.docNo = docNo;
}


public String getIssue(){
    return issue;
}


public String getDetailUrl(){
    return detailUrl;
}


public void setDetailUrl(String detailUrl){
    this.detailUrl = detailUrl;
}


public String getAgentName(){
    return agentName;
}


public void setIssue(String issue){
    this.issue = issue;
}


}