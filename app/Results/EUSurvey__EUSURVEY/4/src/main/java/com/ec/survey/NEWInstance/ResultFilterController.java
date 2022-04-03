package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResultFilterController {

 private ResultFilter resultfilter;


@GetMapping
("/getHash")
public String getHash(@RequestParam(name = "allAnswers") boolean allAnswers){
  return resultfilter.getHash(allAnswers);
}


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return resultfilter.isEmpty();
}


@GetMapping
("/contains")
public boolean contains(@RequestParam(name = "questionId") String questionId,@RequestParam(name = "questionUid") String questionUid,@RequestParam(name = "value") String value,@RequestParam(name = "paUid") String paUid){
  return resultfilter.contains(questionId,questionUid,value,paUid);
}


@GetMapping
("/copy")
public ResultFilter copy(){
  return resultfilter.copy();
}


@PutMapping
("/setVisibleQuestions")
public void setVisibleQuestions(@RequestParam(name = "visibleQuestions") Set<String> visibleQuestions){
resultfilter.setVisibleQuestions(visibleQuestions);
}


@PutMapping
("/setExportedQuestions")
public void setExportedQuestions(@RequestParam(name = "exportedQuestions") Set<String> exportedQuestions){
resultfilter.setExportedQuestions(exportedQuestions);
}


@PutMapping
("/setSurveyId")
public void setSurveyId(@RequestParam(name = "surveyId") int surveyId){
resultfilter.setSurveyId(surveyId);
}


@PutMapping
("/setDefaultQuestions")
public void setDefaultQuestions(@RequestParam(name = "defaultQuestions") Boolean defaultQuestions){
resultfilter.setDefaultQuestions(defaultQuestions);
}


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") Integer userId){
resultfilter.setUserId(userId);
}


@GetMapping
("/merge")
public ResultFilter merge(@RequestParam(name = "copy") ResultFilter copy){
  return resultfilter.merge(copy);
}


@PutMapping
("/setSurveyUid")
public void setSurveyUid(@RequestParam(name = "surveyUid") String surveyUid){
resultfilter.setSurveyUid(surveyUid);
}


@PutMapping
("/setSurveyShortname")
public void setSurveyShortname(@RequestParam(name = "surveyShortname") String surveyShortname){
resultfilter.setSurveyShortname(surveyShortname);
}


@PutMapping
("/setSurveyTitle")
public void setSurveyTitle(@RequestParam(name = "surveyTitle") String surveyTitle){
resultfilter.setSurveyTitle(surveyTitle);
}


@PutMapping
("/setDraftId")
public void setDraftId(@RequestParam(name = "draftId") String draftId){
resultfilter.setDraftId(draftId);
}


@PutMapping
("/setCaseId")
public void setCaseId(@RequestParam(name = "caseId") String caseId){
resultfilter.setCaseId(caseId);
}


@PutMapping
("/setNoTestAnswers")
public void setNoTestAnswers(@RequestParam(name = "noTestAnswers") Boolean noTestAnswers){
resultfilter.setNoTestAnswers(noTestAnswers);
}


@PutMapping
("/setUpdatedFrom")
public void setUpdatedFrom(@RequestParam(name = "updatedFrom") Date updatedFrom){
resultfilter.setUpdatedFrom(updatedFrom);
}


@PutMapping
("/setUpdatedTo")
public void setUpdatedTo(@RequestParam(name = "updatedTo") Date updatedTo){
resultfilter.setUpdatedTo(updatedTo);
}


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
resultfilter.setStatus(status);
}


@PutMapping
("/setAnsweredECFProfileUID")
public void setAnsweredECFProfileUID(@RequestParam(name = "ecfProfileUid") String ecfProfileUid){
resultfilter.setAnsweredECFProfileUID(ecfProfileUid);
}


@PutMapping
("/setCompareToECFProfileUID")
public void setCompareToECFProfileUID(@RequestParam(name = "compareToECFProfileUID") String compareToECFProfileUID){
resultfilter.setCompareToECFProfileUID(compareToECFProfileUID);
}


@PutMapping
("/setSortOrder")
public void setSortOrder(@RequestParam(name = "sortOrder") String sortOrder){
resultfilter.setSortOrder(sortOrder);
}


@PutMapping
("/setSortKey")
public void setSortKey(@RequestParam(name = "sortKey") String sortKey){
resultfilter.setSortKey(sortKey);
}


@PutMapping
("/setLanguages")
public void setLanguages(@RequestParam(name = "languages") Set<String> languages){
resultfilter.setLanguages(languages);
}


@PutMapping
("/clearResultFilter")
public void clearResultFilter(){
resultfilter.clearResultFilter();
}


@PutMapping
("/clearSelectedQuestions")
public void clearSelectedQuestions(){
resultfilter.clearSelectedQuestions();
}


@PutMapping
("/addExportedQuestion")
public void addExportedQuestion(@RequestParam(name = "question") String question){
resultfilter.addExportedQuestion(question);
}


@PutMapping
("/setGeneratedTo")
public void setGeneratedTo(@RequestParam(name = "generatedTo") Date generatedTo){
resultfilter.setGeneratedTo(generatedTo);
}


@PutMapping
("/setGeneratedFrom")
public void setGeneratedFrom(@RequestParam(name = "generatedFrom") Date generatedFrom){
resultfilter.setGeneratedFrom(generatedFrom);
}


@PutMapping
("/setUser")
public void setUser(@RequestParam(name = "user") String user){
resultfilter.setUser(user);
}


@PutMapping
("/setInvitation")
public void setInvitation(@RequestParam(name = "invitation") String invitation){
resultfilter.setInvitation(invitation);
}


@PutMapping
("/setSurveyStatus")
public void setSurveyStatus(@RequestParam(name = "surveyStatus") String surveyStatus){
resultfilter.setSurveyStatus(surveyStatus);
}


@PutMapping
("/setSurveyEndDateFrom")
public void setSurveyEndDateFrom(@RequestParam(name = "surveyEndDateFrom") Date surveyEndDateFrom){
resultfilter.setSurveyEndDateFrom(surveyEndDateFrom);
}


@PutMapping
("/setSurveyEndDateTo")
public void setSurveyEndDateTo(@RequestParam(name = "surveyEndDateTo") Date surveyEndDateTo){
resultfilter.setSurveyEndDateTo(surveyEndDateTo);
}


}