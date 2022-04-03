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


public boolean isFromIPM(){
    return isFromIPM;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isFromIPM"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isInvalidCodeFound(){
    return invalidCodeFound;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isInvalidCodeFound"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}