package com.ec.survey.model.survey.quiz;
 import com.ec.survey.model.survey.ScoringItem;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class QuizResult {

 private  long serialVersionUID;

 private  Integer score;

 private  Integer maximumScore;

 private  Map<String,Integer> questionScores;

 private  Map<String,Integer> questionMaximumScores;

 private  Map<String,ScoringItem> questionScoringItems;

 private  Set<String> partiallyAnswersMultipleChoiceQuestions;

 private  Map<String,String> sectionScores;


public void setQuestionScoringItems(Map<String,ScoringItem> questionScoringItems){
    this.questionScoringItems = questionScoringItems;
}


public String getSectionScore(String uid){
    if (sectionScores.containsKey(uid))
        return sectionScores.get(uid);
    return null;
}


public Map<String,String> getSectionScores(){
    return sectionScores;
}


public Map<String,Integer> getQuestionMaximumScores(){
    return questionMaximumScores;
}


public void setMaximumScore(Integer maximumScore){
    this.maximumScore = maximumScore;
}


public Map<String,ScoringItem> getQuestionScoringItems(){
    return questionScoringItems;
}


public int getSectionScoreValue(String uid){
    if (sectionScores.containsKey(uid)) {
        return Integer.parseInt(sectionScores.get(uid).substring(0, sectionScores.get(uid).indexOf('/')));
    }
    return 0;
}


public void setSectionScores(Map<String,String> sectionScores){
    this.sectionScores = sectionScores;
}


public void setQuestionScores(Map<String,Integer> questionScores){
    this.questionScores = questionScores;
}


public int getQuestionScore(String uid){
    if (questionScores.containsKey(uid))
        return questionScores.get(uid);
    return 0;
}


public ScoringItem getQuestionScoringItem(String uid){
    if (questionScoringItems.containsKey(uid))
        return questionScoringItems.get(uid);
    return null;
}


public void setPartiallyAnswersMultipleChoiceQuestions(Set<String> partiallyAnswersMultipleChoiceQuestions){
    this.partiallyAnswersMultipleChoiceQuestions = partiallyAnswersMultipleChoiceQuestions;
}


public int getMaxSectionScore(){
    int result = 0;
    for (String s : sectionScores.values()) {
        String max = s.substring(s.indexOf('/') + 1);
        int m = Integer.parseInt(max);
        if (m > result)
            result = m;
    }
    return result;
}


public Map<String,Integer> getQuestionScores(){
    return questionScores;
}


public void setQuestionMaximumScores(Map<String,Integer> questionMaximumScores){
    this.questionMaximumScores = questionMaximumScores;
}


public Set<String> getPartiallyAnswersMultipleChoiceQuestions(){
    return partiallyAnswersMultipleChoiceQuestions;
}


public int getQuestionMaximumScore(String uid){
    if (questionMaximumScores.containsKey(uid))
        return questionMaximumScores.get(uid);
    return 0;
}


public Integer getScore(){
    return score;
}


public Integer getMaximumScore(){
    return maximumScore;
}


public void setScore(Integer score){
    this.score = score;
}


}