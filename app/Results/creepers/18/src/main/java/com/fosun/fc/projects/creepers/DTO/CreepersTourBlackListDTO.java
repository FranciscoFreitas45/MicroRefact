package com.fosun.fc.projects.creepers.DTO;
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


public String getDecision(){
    return decision;
}


public String getName(){
    return name;
}


public String getGuideNo(){
    return guideNo;
}


public String getPeriod(){
    return period;
}


public String getDocNo(){
    return docNo;
}


public String getType(){
    return type;
}


public String getIssue(){
    return issue;
}


public String getDetailUrl(){
    return detailUrl;
}


public String getAgentName(){
    return agentName;
}


}