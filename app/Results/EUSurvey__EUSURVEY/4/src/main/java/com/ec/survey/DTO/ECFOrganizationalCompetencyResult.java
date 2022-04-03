package com.ec.survey.DTO;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFOrganizationalCompetencyResult implements Comparable{

 private  String competencyName;

 private  Float competencyAverageTarget;

 private  Float competencyAverageScore;

 private  Integer competencyMaxTarget;

 private  Integer competencyMaxScore;

 private  String competencyTypeUid;

 private  Integer order;


public String getCompetencyName(){
    return competencyName;
}


public Integer getCompetencyMaxTarget(){
    return competencyMaxTarget;
}


public String getCompetencyTypeUid(){
    return competencyTypeUid;
}


public Float getCompetencyAverageScore(){
    return competencyAverageScore;
}


public Integer getOrder(){
    return order;
}


public Float getCompetencyAverageTarget(){
    return competencyAverageTarget;
}


public Integer getCompetencyMaxScore(){
    return competencyMaxScore;
}


}