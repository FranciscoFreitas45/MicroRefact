package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ImportResultController {

 private ImportResult importresult;

 private ImportResult importresult;


@GetMapping
("/isInvalidCodeFound")
public boolean isInvalidCodeFound(){
  return importresult.isInvalidCodeFound();
}


@PutMapping
("/setSurvey")
public void setSurvey(@RequestParam(name = "survey") Survey survey){
importresult.setSurvey(survey);
}


@PutMapping
("/setTranslations")
public void setTranslations(@RequestParam(name = "translations") List<Translations> translations){
importresult.setTranslations(translations);
}


@PutMapping
("/setActiveComments")
public void setActiveComments(@RequestParam(name = "activeComments") List<AnswerComment> activeComments){
importresult.setActiveComments(activeComments);
}


@PutMapping
("/setComments")
public void setComments(@RequestParam(name = "comments") List<AnswerComment> comments){
importresult.setComments(comments);
}


@PutMapping
("/setActiveExplanations")
public void setActiveExplanations(@RequestParam(name = "activeExplanations") List<AnswerExplanation> activeExplanations){
importresult.setActiveExplanations(activeExplanations);
}


@PutMapping
("/setExplanations")
public void setExplanations(@RequestParam(name = "explanations") List<AnswerExplanation> explanations){
importresult.setExplanations(explanations);
}


@PutMapping
("/setActiveFiles")
public void setActiveFiles(@RequestParam(name = "activeFiles") Map<Integer,List<File>> activeFiles){
importresult.setActiveFiles(activeFiles);
}


@PutMapping
("/setFiles")
public void setFiles(@RequestParam(name = "files") Map<Integer,List<File>> files){
importresult.setFiles(files);
}


@PutMapping
("/setActiveAnswerSets")
public void setActiveAnswerSets(@RequestParam(name = "activeAnswerSets") List<List<AnswerSet>> activeAnswerSets){
importresult.setActiveAnswerSets(activeAnswerSets);
}


@PutMapping
("/setAnswerSets")
public void setAnswerSets(@RequestParam(name = "answerSets") List<AnswerSet> answerSets){
importresult.setAnswerSets(answerSets);
}


@PutMapping
("/setActiveTranslations")
public void setActiveTranslations(@RequestParam(name = "activeTranslations") List<Translations> activeTranslations){
importresult.setActiveTranslations(activeTranslations);
}


@PutMapping
("/setActiveSurvey")
public void setActiveSurvey(@RequestParam(name = "activeSurvey") Survey activeSurvey){
importresult.setActiveSurvey(activeSurvey);
}


@PutMapping
("/setFromIPM")
public void setFromIPM(@RequestParam(name = "isFromIPM") boolean isFromIPM){
importresult.setFromIPM(isFromIPM);
}


@PutMapping
("/setInvalidCodeFound")
public void setInvalidCodeFound(@RequestParam(name = "invalidCodeFound") boolean invalidCodeFound){
importresult.setInvalidCodeFound(invalidCodeFound);
}


}