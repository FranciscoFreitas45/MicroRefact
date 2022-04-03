package com.ec.survey.model.delphi;
 import java.util.HashMap;
import java.util.Map;
public class NumberQuestionStatistics {

 private  int numberVotes;

 private  boolean questionFound;

 private  Map<String,Integer> valuesMagnitude;

public NumberQuestionStatistics() {
    this.numberVotes = 0;
    this.questionFound = false;
    this.valuesMagnitude = new HashMap<>();
}public NumberQuestionStatistics(int numberVotes, boolean questionFound) {
    this.numberVotes = numberVotes;
    this.questionFound = questionFound;
    this.valuesMagnitude = new HashMap<>();
}
public void setQuestionFound(boolean questionFound){
    this.questionFound = questionFound;
}


public boolean isQuestionFound(){
    return questionFound;
}


public Map<String,Integer> getValuesMagnitude(){
    return valuesMagnitude;
}


public int getNumberVotes(){
    return numberVotes;
}


public void setNumberVotes(int numberVotes){
    this.numberVotes = numberVotes;
}


public void setValuesMagnitude(Map<String,Integer> valuesMagnitude){
    this.valuesMagnitude = valuesMagnitude;
}


public void incrementNumberVotes(){
    this.numberVotes += 1;
}


}