package com.ec.survey.model.survey.ecf;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFGlobalTotalResult {

@JsonProperty("competencyName")
 private  String competencyName;

@JsonProperty("totalTargetScore")
 private  Integer totalTargetScore;

@JsonProperty("totalScores")
 private  List<Integer> totalScores;

@JsonProperty("totalGaps")
 private  List<Integer> totalGaps;


public Integer getTotalTargetScore(){
    return totalTargetScore;
}


public void setTotalScores(List<Integer> totalScores){
    this.totalScores = totalScores;
}


public List<Integer> getTotalScores(){
    return totalScores;
}


public String getCompetencyName(){
    return competencyName;
}


public List<Integer> getTotalGaps(){
    return totalGaps;
}


public void addTotalGap(Integer totalGap){
    this.totalGaps.add(totalGap);
}


public void setCompetencyName(String competencyName){
    this.competencyName = competencyName;
}


public void addTotalScore(Integer totalScore){
    this.totalScores.add(totalScore);
}


@Override
public String toString(){
    return "ECFGlobalTotalResult [competencyName=" + competencyName + ", totalTargetScore=" + totalTargetScore + ", totalScores=" + totalScores + ", totalGaps=" + totalGaps + "]";
}


public void setTotalGaps(List<Integer> totalGaps){
    this.totalGaps = totalGaps;
}


public void setTotalTargetScore(Integer totalTargetScore){
    this.totalTargetScore = totalTargetScore;
}


}