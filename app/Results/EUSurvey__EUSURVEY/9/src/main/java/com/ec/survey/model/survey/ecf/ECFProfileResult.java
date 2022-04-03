package com.ec.survey.model.survey.ecf;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFProfileResult {

@JsonProperty("name")
 private  String profileName;

@JsonProperty("competencyResults")
 private  List<ECFProfileCompetencyResult> competencyResults;

@JsonProperty("numberOfAnswers")
 private  Integer numberOfAnswers;


public void setNumberOfAnswers(Integer numberOfAnswers){
    this.numberOfAnswers = numberOfAnswers;
}


public void setProfileName(String profileName){
    this.profileName = profileName;
}


public Integer getNumberOfAnswers(){
    return numberOfAnswers;
}


public String getProfileName(){
    return profileName;
}


public void setCompetencyResults(List<ECFProfileCompetencyResult> competencyResults){
    this.competencyResults = competencyResults;
}


public List<ECFProfileCompetencyResult> getCompetencyResults(){
    return competencyResults;
}


@Override
public String toString(){
    return "ECFProfileResult [profileName=" + profileName + ", competencyResults=" + competencyResults + ", numberOfAnswers=" + numberOfAnswers + "]";
}


public void addIndividualResults(ECFProfileCompetencyResult competencyResult){
    this.competencyResults.add(competencyResult);
}


}