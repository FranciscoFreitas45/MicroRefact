package com.ec.survey.DTO;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFGlobalCompetencyResult implements Comparable<ECFGlobalCompetencyResult>{

 private  String competencyName;

 private  Integer competencyTargetScore;

 private  List<Integer> competencyScores;

 private  List<Integer> competencyScoreGaps;

 private  List<String> participantsNames;

 private  List<String> participantContributionUIDs;

 private  Integer order;


public String getCompetencyName(){
    return competencyName;
}


public List<String> getParticipantsNames(){
    return participantsNames;
}


public List<Integer> getCompetencyScoreGaps(){
    return competencyScoreGaps;
}


public Integer getOrder(){
    return order;
}


public List<String> getParticipantContributionUIDs(){
    return participantContributionUIDs;
}


public List<Integer> getCompetencyScores(){
    return competencyScores;
}


public Integer getCompetencyTargetScore(){
    return competencyTargetScore;
}


}