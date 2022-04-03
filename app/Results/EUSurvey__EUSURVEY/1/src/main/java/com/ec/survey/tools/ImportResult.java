package com.ec.survey.tools;
 import com.ec.survey.model.AnswerComment;
import com.ec.survey.model.AnswerExplanation;
import com.ec.survey.model.AnswerSet;
import com.ec.survey.model.Translations;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.model.survey.base.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.Survey;
public class ImportResult {

 private  Survey survey;

 private  Survey activeSurvey;

 private  boolean isFromIPM;

 private  boolean invalidCodeFound;

 private  List<Translations> translations;

 private  List<Translations> activeTranslations;

 private  List<List<AnswerSet>> activeAnswerSets;

 private  List<AnswerSet> answerSets;

 private  Map<Integer,List<File>> activeFiles;

 private  Map<Integer,List<File>> files;

 private  Map<String,Integer> originalIdsToNewIds;

 private  Map<Integer,List<String>> originalDependencies;

 private  Map<String,List<String>> originalMatrixDependencies;

 private  Map<Integer,List<Integer>> additionalElements;

 private  Map<Integer,Survey> oldSurveys;

 private  Map<Integer,List<Translations>> oldTranslations;

 private  List<AnswerExplanation> explanations;

 private  List<AnswerExplanation> activeExplanations;

 private  List<AnswerComment> comments;

 private  List<AnswerComment> activeComments;


public Survey getActiveSurvey(){
    return activeSurvey;
}


public void setInvalidCodeFound(boolean invalidCodeFound){
    this.invalidCodeFound = invalidCodeFound;
}


public void setActiveComments(List<AnswerComment> activeComments){
    this.activeComments = activeComments;
}


public void setActiveTranslations(List<Translations> activeTranslations){
    this.activeTranslations = activeTranslations;
}


public void setOriginalDependencies(Map<Integer,List<String>> originalDependencies){
    this.originalDependencies = originalDependencies;
}


public void setTranslations(List<Translations> translations){
    this.translations = translations;
}


public void setFromIPM(boolean isFromIPM){
    this.isFromIPM = isFromIPM;
}


public void setFiles(Map<Integer,List<File>> files){
    this.files = files;
}


public List<AnswerExplanation> getActiveExplanations(){
    return activeExplanations;
}


public void setActiveExplanations(List<AnswerExplanation> activeExplanations){
    this.activeExplanations = activeExplanations;
}


public Map<Integer,List<String>> getOriginalDependencies(){
    return originalDependencies;
}


public List<AnswerSet> getAnswerSets(){
    return answerSets;
}


public boolean isFromIPM(){
    return isFromIPM;
}


public void setActiveAnswerSets(List<List<AnswerSet>> activeAnswerSets){
    this.activeAnswerSets = activeAnswerSets;
}


public List<AnswerComment> getComments(){
    return comments;
}


public List<List<AnswerSet>> getActiveAnswerSets(){
    return activeAnswerSets;
}


public Map<Integer,List<File>> getActiveFiles(){
    return activeFiles;
}


public void setComments(List<AnswerComment> comments){
    this.comments = comments;
}


public void setOriginalIdsToNewIds(Map<String,Integer> originalIdsToNewIds){
    this.originalIdsToNewIds = originalIdsToNewIds;
}


public boolean isInvalidCodeFound(){
    return invalidCodeFound;
}


public Map<Integer,Survey> getOldSurveys(){
    return oldSurveys;
}


public void setActiveFiles(Map<Integer,List<File>> activeFiles){
    this.activeFiles = activeFiles;
}


public void setExplanations(List<AnswerExplanation> explanations){
    this.explanations = explanations;
}


public void setAnswerSets(List<AnswerSet> answerSets){
    this.answerSets = answerSets;
}


public Map<String,Integer> getOriginalIdsToNewIds(){
    return originalIdsToNewIds;
}


public List<AnswerExplanation> getExplanations(){
    return explanations;
}


public List<Translations> getActiveTranslations(){
    return activeTranslations;
}


public Survey getSurvey(){
    return survey;
}


public Map<Integer,List<File>> getFiles(){
    return files;
}


public void setOldSurveys(Map<Integer,Survey> oldSurveys){
    this.oldSurveys = oldSurveys;
}


public void setSurvey(Survey survey){
    this.survey = survey;
}


public void setOriginalMatrixDependencies(Map<String,List<String>> originalMatrixDependencies){
    this.originalMatrixDependencies = originalMatrixDependencies;
}


public List<AnswerComment> getActiveComments(){
    return activeComments;
}


public Map<Integer,List<Translations>> getOldTranslations(){
    return oldTranslations;
}


public void setAdditionalElements(Map<Integer,List<Integer>> additionalElements){
    this.additionalElements = additionalElements;
}


public Map<Integer,List<Integer>> getAdditionalElements(){
    return additionalElements;
}


public Map<String,List<String>> getOriginalMatrixDependencies(){
    return originalMatrixDependencies;
}


public void setOldTranslations(Map<Integer,List<Translations>> oldTranslations){
    this.oldTranslations = oldTranslations;
}


public List<Translations> getTranslations(){
    return translations;
}


public void setActiveSurvey(Survey activeSurvey){
    this.activeSurvey = activeSurvey;
}


}