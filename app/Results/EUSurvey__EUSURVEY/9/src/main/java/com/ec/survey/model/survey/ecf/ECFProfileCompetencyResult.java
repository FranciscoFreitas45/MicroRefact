package com.ec.survey.model.survey.ecf;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFProfileCompetencyResult implements Comparable{

@JsonProperty("competencyName")
 private  String competencyName;

@JsonProperty("competencyTargetScore")
 private  Integer competencyTargetScore;

@JsonProperty("competencyAverageScore")
 private  Float competencyAverageScore;

@JsonProperty("competencyMaxScore")
 private  Integer competencyMaxScore;

@JsonProperty("competencyScoreGap")
 private  Integer competencyScoreGap;

 private  Integer order;


public String getCompetencyName(){
    return competencyName;
}


public void setCompetencyTargetScore(Integer competencyTargetScore){
    this.competencyTargetScore = competencyTargetScore;
}


public void setCompetencyMaxScore(Integer competencyMaxScore){
    this.competencyMaxScore = competencyMaxScore;
}


@Override
public int compareTo(Object otherObject){
    if (otherObject instanceof ECFProfileCompetencyResult) {
        ECFProfileCompetencyResult otherResult = (ECFProfileCompetencyResult) otherObject;
        return this.getOrder().compareTo(otherResult.getOrder());
    } else {
        return 0;
    }
}


public void setOrder(Integer order){
    this.order = order;
}


public void setCompetencyScoreGap(Integer competencyScoreGap){
    this.competencyScoreGap = competencyScoreGap;
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


@Override
public String toString(){
    return "ECFProfileCompetencyResult [competencyName=" + competencyName + ", competencyTargetScore=" + competencyTargetScore + ", competencyAverageScore=" + competencyAverageScore + ", competencyMaxScore=" + competencyMaxScore + ", competencyScoreGap=" + competencyScoreGap + ", order=" + order + "]";
}


public Integer getCompetencyScoreGap(){
    return this.competencyScoreGap;
}


public Integer getCompetencyTargetScore(){
    return competencyTargetScore;
}


public Integer getCompetencyMaxScore(){
    return competencyMaxScore;
}


}