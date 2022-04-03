package com.ec.survey.DTO;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFProfileCompetencyResult implements Comparable{

 private  String competencyName;

 private  Integer competencyTargetScore;

 private  Float competencyAverageScore;

 private  Integer competencyMaxScore;

 private  Integer competencyScoreGap;

 private  Integer order;


public String getCompetencyName(){
    return competencyName;
}


public Float getCompetencyAverageScore(){
    return competencyAverageScore;
}


public Integer getOrder(){
    return order;
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