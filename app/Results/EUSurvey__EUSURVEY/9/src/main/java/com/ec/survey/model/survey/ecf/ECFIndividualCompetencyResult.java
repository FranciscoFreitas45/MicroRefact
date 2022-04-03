package com.ec.survey.model.survey.ecf;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFIndividualCompetencyResult implements Comparable{

@JsonProperty("name")
 private  String competencyName;

@JsonProperty("score")
 private  Integer competencyScore;

@JsonProperty("targetScore")
 private  Integer competencyTargetScore;

@JsonProperty("scoreGap")
 private  Integer competencyScoreGap;

@JsonProperty("typeUUID")
 private  String typeUUID;

 private  Integer order;

@JsonIgnore
 private  List<Integer> questionsScores;


public String getTypeUUID(){
    return typeUUID;
}


public String getCompetencyName(){
    return competencyName;
}


public void setCompetencyTargetScore(Integer competencyTargetScore){
    this.competencyTargetScore = competencyTargetScore;
}


@Override
public int compareTo(Object otherObject){
    if (otherObject instanceof ECFIndividualCompetencyResult) {
        ECFIndividualCompetencyResult otherResult = (ECFIndividualCompetencyResult) otherObject;
        return this.getOrder().compareTo(otherResult.getOrder());
    } else {
        return 0;
    }
}


public void addCompetencyScore(Integer oneQuestionScore){
    this.questionsScores.add(oneQuestionScore);
}


public Integer getCompetencyScore(){
    return competencyScore;
}


public void setOrder(Integer order){
    this.order = order;
}


public void setTypeUUID(String typeUUID){
    this.typeUUID = typeUUID;
}


public void setCompetencyScore(Integer competencyScore){
    this.competencyScore = competencyScore;
}


public Integer getOrder(){
    return order;
}


public void setCompetencyName(String competencyName){
    this.competencyName = competencyName;
}


@Override
public String toString(){
    return "ECFIndividualCompetencyResult [competencyName=" + competencyName + ", competencyScore=" + competencyScore + ", competencyScoreGap=" + competencyScoreGap + ", competencyTargetScore=" + competencyTargetScore + ", order=" + order + ", questionsScores=" + questionsScores + ", typeUUID=" + typeUUID + "]";
}


public Integer getCompetencyScoreGap(){
    this.competencyScoreGap = this.competencyScore - this.competencyTargetScore;
    return this.competencyScoreGap;
}


public Integer getCompetencyTargetScore(){
    return competencyTargetScore;
}


}