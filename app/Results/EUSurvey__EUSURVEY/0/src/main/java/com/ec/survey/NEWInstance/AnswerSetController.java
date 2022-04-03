package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnswerSetController {

 private AnswerSet answerset;


@GetMapping
("/getSurvey")
public Survey getSurvey(){
  return answerset.getSurvey();
}


@PutMapping
("/setChangeExplanationText")
public void setChangeExplanationText(@RequestParam(name = "changeExplanationText") Boolean changeExplanationText){
answerset.setChangeExplanationText(changeExplanationText);
}


@PutMapping
("/setChangedForMedian")
public void setChangedForMedian(@RequestParam(name = "changedForMedian") Boolean changedForMedian){
answerset.setChangedForMedian(changedForMedian);
}


@PutMapping
("/setInvitationId")
public void setInvitationId(@RequestParam(name = "invitationId") String invitationId){
answerset.setInvitationId(invitationId);
}


@PutMapping
("/setResponderEmail")
public void setResponderEmail(@RequestParam(name = "responderEmail") String responderEmail){
answerset.setResponderEmail(responderEmail);
}


@GetMapping
("/serialize")
public String serialize(){
  return answerset.serialize();
}


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
answerset.setId(id);
}


@PutMapping
("/setDate")
public void setDate(@RequestParam(name = "date") Date date){
answerset.setDate(date);
}


@PutMapping
("/setUpdateDate")
public void setUpdateDate(@RequestParam(name = "updateDate") Date updateDate){
answerset.setUpdateDate(updateDate);
}


@PutMapping
("/setUniqueCode")
public void setUniqueCode(@RequestParam(name = "uniqueCode") String uniqueCode){
answerset.setUniqueCode(uniqueCode);
}


@PutMapping
("/setLanguageCode")
public void setLanguageCode(@RequestParam(name = "languageCode") String languageCode){
answerset.setLanguageCode(languageCode);
}


@PutMapping
("/setScore")
public void setScore(@RequestParam(name = "score") Integer score){
answerset.setScore(score);
}


@PutMapping
("/addAnswer")
public void addAnswer(@RequestParam(name = "answer") Answer answer){
answerset.addAnswer(answer);
}


@PutMapping
("/setSurvey")
public void setSurvey(@RequestParam(name = "survey") Survey survey){
answerset.setSurvey(survey);
}


@PutMapping
("/setSurveyId")
public void setSurveyId(@RequestParam(name = "surveyId") int surveyId){
answerset.setSurveyId(surveyId);
}


@PutMapping
("/setIsDraft")
public void setIsDraft(@RequestParam(name = "isDraft") boolean isDraft){
answerset.setIsDraft(isDraft);
}


@PutMapping
("/setEcfProfileUid")
public void setEcfProfileUid(@RequestParam(name = "ecfProfileUid") String ecfProfileUid){
answerset.setEcfProfileUid(ecfProfileUid);
}


@PutMapping
("/setEcfTotalScore")
public void setEcfTotalScore(@RequestParam(name = "ecfTotalScore") Integer ecfTotalScore){
answerset.setEcfTotalScore(ecfTotalScore);
}


@PutMapping
("/setEcfTotalGap")
public void setEcfTotalGap(@RequestParam(name = "ecfTotalGap") Integer ecfTotalGap){
answerset.setEcfTotalGap(ecfTotalGap);
}


@PutMapping
("/setIP")
public void setIP(@RequestParam(name = "iP") String iP){
answerset.setIP(iP);
}


@PutMapping
("/setDisclaimerMinimized")
public void setDisclaimerMinimized(@RequestParam(name = "disclaimerMinimized") Boolean disclaimerMinimized){
answerset.setDisclaimerMinimized(disclaimerMinimized);
}


@PutMapping
("/setWcagMode")
public void setWcagMode(@RequestParam(name = "wcagMode") Boolean wcagMode){
answerset.setWcagMode(wcagMode);
}


@PutMapping
("/clearAnswers")
public void clearAnswers(@RequestParam(name = "question") Element question){
answerset.clearAnswers(question);
}


}