package com.ec.survey.model.survey.ecf;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFOrganizationalCompetencyResult implements Comparable{

@JsonProperty("competencyName")
 private  String competencyName;

@JsonProperty("competencyAverageTarget")
 private  Float competencyAverageTarget;

@JsonProperty("competencyAverageScore")
 private  Float competencyAverageScore;

@JsonProperty("competencyMaxTarget")
 private  Integer competencyMaxTarget;

@JsonProperty("competencyMaxScore")
 private  Integer competencyMaxScore;

@JsonProperty("competencyTypeUid")
 private  String competencyTypeUid;

 private  Integer order;


public String getCompetencyName(){
    return competencyName;
}


public void setCompetencyMaxTarget(Integer competencyMaxTarget){
    this.competencyMaxTarget = competencyMaxTarget;
}


public void setCompetencyMaxScore(Integer competencyMaxScore){
    this.competencyMaxScore = competencyMaxScore;
}


@Override
public int compareTo(Object otherObject){
    if (otherObject instanceof ECFOrganizationalCompetencyResult) {
        ECFOrganizationalCompetencyResult otherResult = (ECFOrganizationalCompetencyResult) otherObject;
        return this.getOrder().compareTo(otherResult.getOrder());
    } else {
        return 0;
    }
}


public Integer getCompetencyMaxTarget(){
    return competencyMaxTarget;
}


public void setOrder(Integer order){
    this.order = order;
}


public String getCompetencyTypeUid(){
    return competencyTypeUid;
}


public void setCompetencyAverageScore(Float competencyAverageScore){
    this.competencyAverageScore = competencyAverageScore;
}


public Float getCompetencyAverageScore(){
    return competencyAverageScore;
}


public Integer getOrder(){
    return order;
}


public void setCompetencyName(String competencyName){
    this.competencyName = competencyName;
}


public Float getCompetencyAverageTarget(){
    return competencyAverageTarget;
}


public void setCompetencyTypeUid(String competencyTypeUid){
    this.competencyTypeUid = competencyTypeUid;
}


@Override
public String toString(){
    return "ECFOrganizationalCompetencyResult [competencyAverageScore=" + competencyAverageScore + ", competencyAverageTarget=" + competencyAverageTarget + ", competencyMaxScore=" + competencyMaxScore + ", competencyMaxTarget=" + competencyMaxTarget + ", competencyName=" + competencyName + ", competencyTypeUid=" + competencyTypeUid + ", order=" + order + "]";
}


public void setCompetencyAverageTarget(Float competencyAverageTarget){
    this.competencyAverageTarget = competencyAverageTarget;
}


public Integer getCompetencyMaxScore(){
    return competencyMaxScore;
}


}