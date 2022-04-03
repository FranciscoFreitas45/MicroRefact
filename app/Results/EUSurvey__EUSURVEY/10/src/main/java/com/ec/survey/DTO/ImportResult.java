package com.ec.survey.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public Survey getActiveSurvey(){
    return activeSurvey;
}


public List<AnswerExplanation> getActiveExplanations(){
    return activeExplanations;
}


public Map<Integer,List<String>> getOriginalDependencies(){
    return originalDependencies;
}


public List<AnswerSet> getAnswerSets(){
    return answerSets;
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


public Map<Integer,Survey> getOldSurveys(){
    return oldSurveys;
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


public List<AnswerComment> getActiveComments(){
    return activeComments;
}


public Map<Integer,List<Translations>> getOldTranslations(){
    return oldTranslations;
}


public Map<Integer,List<Integer>> getAdditionalElements(){
    return additionalElements;
}


public Map<String,List<String>> getOriginalMatrixDependencies(){
    return originalMatrixDependencies;
}


public List<Translations> getTranslations(){
    return translations;
}


public void setSurvey(Survey survey){
    this.survey = survey;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurvey"))

.queryParam("survey",survey)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTranslations(List<Translations> translations){
    this.translations = translations;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTranslations"))

.queryParam("translations",translations)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActiveComments(List<AnswerComment> activeComments){
    this.activeComments = activeComments;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActiveComments"))

.queryParam("activeComments",activeComments)
;
restTemplate.put(builder.toUriString(),null);
}


public void setComments(List<AnswerComment> comments){
    this.comments = comments;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setComments"))

.queryParam("comments",comments)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActiveExplanations(List<AnswerExplanation> activeExplanations){
    this.activeExplanations = activeExplanations;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActiveExplanations"))

.queryParam("activeExplanations",activeExplanations)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExplanations(List<AnswerExplanation> explanations){
    this.explanations = explanations;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExplanations"))

.queryParam("explanations",explanations)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActiveFiles(Map<Integer,List<File>> activeFiles){
    this.activeFiles = activeFiles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActiveFiles"))

.queryParam("activeFiles",activeFiles)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFiles(Map<Integer,List<File>> files){
    this.files = files;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFiles"))

.queryParam("files",files)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActiveAnswerSets(List<List<AnswerSet>> activeAnswerSets){
    this.activeAnswerSets = activeAnswerSets;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActiveAnswerSets"))

.queryParam("activeAnswerSets",activeAnswerSets)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAnswerSets(List<AnswerSet> answerSets){
    this.answerSets = answerSets;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAnswerSets"))

.queryParam("answerSets",answerSets)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActiveTranslations(List<Translations> activeTranslations){
    this.activeTranslations = activeTranslations;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActiveTranslations"))

.queryParam("activeTranslations",activeTranslations)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActiveSurvey(Survey activeSurvey){
    this.activeSurvey = activeSurvey;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActiveSurvey"))

.queryParam("activeSurvey",activeSurvey)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFromIPM(boolean isFromIPM){
    this.isFromIPM = isFromIPM;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFromIPM"))

.queryParam("isFromIPM",isFromIPM)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInvalidCodeFound(boolean invalidCodeFound){
    this.invalidCodeFound = invalidCodeFound;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInvalidCodeFound"))

.queryParam("invalidCodeFound",invalidCodeFound)
;
restTemplate.put(builder.toUriString(),null);
}


}