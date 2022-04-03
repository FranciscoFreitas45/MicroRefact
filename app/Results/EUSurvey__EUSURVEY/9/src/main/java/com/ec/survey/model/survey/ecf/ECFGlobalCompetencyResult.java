package com.ec.survey.model.survey.ecf;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFGlobalCompetencyResult implements Comparable<ECFGlobalCompetencyResult>{

@JsonProperty("name")
 private  String competencyName;

@JsonProperty("targetScore")
 private  Integer competencyTargetScore;

@JsonProperty("scores")
 private  List<Integer> competencyScores;

@JsonProperty("scoreGaps")
 private  List<Integer> competencyScoreGaps;

@JsonProperty("participantNames")
 private  List<String> participantsNames;

@JsonProperty("participantContributionUIDs")
 private  List<String> participantContributionUIDs;

 private  Integer order;


public String getCompetencyName(){
    return competencyName;
}


public void setCompetencyTargetScore(Integer competencyTargetScore){
    this.competencyTargetScore = competencyTargetScore;
}


public List<String> getParticipantsNames(){
    return participantsNames;
}


public void setParticipantContributionUIDs(List<String> participantContributionUIDs){
    this.participantContributionUIDs = participantContributionUIDs;
}


public void addParticipantsName(String participantsName){
    this.participantsNames.add(participantsName);
}


public void addParticipantContributionUID(String participantContributionUID){
    this.participantContributionUIDs.add(participantContributionUID);
}


public void setCompetencyScores(List<Integer> competencyScores){
    this.competencyScores = competencyScores;
}


public void setParticipantsNames(List<String> participantsNames){
    this.participantsNames = participantsNames;
}


@Override
public int compareTo(ECFGlobalCompetencyResult otherObject){
    return this.getOrder().compareTo(otherObject.getOrder());
}


public void addCompetencyScore(Integer competencyScore){
    this.competencyScores.add(competencyScore);
}


public void addCompetencyScoreGap(Integer scoreGap){
    this.competencyScoreGaps.add(scoreGap);
}


public void setOrder(Integer order){
    this.order = order;
}


public List<Integer> getCompetencyScoreGaps(){
    return competencyScoreGaps;
}


public Integer getOrder(){
    return order;
}


public void setCompetencyName(String competencyName){
    this.competencyName = competencyName;
}


public List<String> getParticipantContributionUIDs(){
    return participantContributionUIDs;
}


public List<Integer> getCompetencyScores(){
    return competencyScores;
}


@Override
public String toString(){
    return "ECFGlobalCompetencyResult [competencyName=" + competencyName + ", competencyTargetScore=" + competencyTargetScore + ", competencyScores=" + competencyScores + ", competencyScoreGaps=" + competencyScoreGaps + ", participantsNames=" + participantsNames + ", order=" + order + "]";
}


public Integer getCompetencyTargetScore(){
    return competencyTargetScore;
}


public void setCompetencyScoreGaps(List<Integer> competencyScoreGaps){
    this.competencyScoreGaps = competencyScoreGaps;
}


}