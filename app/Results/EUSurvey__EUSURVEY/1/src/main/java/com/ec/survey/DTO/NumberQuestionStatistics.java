package com.ec.survey.DTO;
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
public Map<String,Integer> getValuesMagnitude(){
    return valuesMagnitude;
}


public int getNumberVotes(){
    return numberVotes;
}


}