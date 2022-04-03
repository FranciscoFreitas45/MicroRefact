package com.ec.survey.DTO;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFProfileResult {

 private  String profileName;

 private  List<ECFProfileCompetencyResult> competencyResults;

 private  Integer numberOfAnswers;


public Integer getNumberOfAnswers(){
    return numberOfAnswers;
}


public String getProfileName(){
    return profileName;
}


public List<ECFProfileCompetencyResult> getCompetencyResults(){
    return competencyResults;
}


}