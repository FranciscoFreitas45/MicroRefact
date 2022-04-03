package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnswerServiceController {

 private AnswerService answerservice;


@GetMapping
("/serializeOriginal")
public String serializeOriginal(@RequestParam(name = "id") Integer id){
  return answerservice.serializeOriginal(id);
}


@PutMapping
("/internalSaveAnswerSet")
public void internalSaveAnswerSet(@RequestParam(name = "answerSet") AnswerSet answerSet,@RequestParam(name = "fileDir") String fileDir,@RequestParam(name = "draftid") String draftid,@RequestParam(name = "invalidateExportsAndStatistics") boolean invalidateExportsAndStatistics,@RequestParam(name = "createAttendees") boolean createAttendees){
answerservice.internalSaveAnswerSet(answerSet,fileDir,draftid,invalidateExportsAndStatistics,createAttendees);
}


@GetMapping
("/getNumberOfAnswerSetsPublished")
public int getNumberOfAnswerSetsPublished(@RequestParam(name = "surveyname") String surveyname,@RequestParam(name = "uid") String uid){
  return answerservice.getNumberOfAnswerSetsPublished(surveyname,uid);
}


@GetMapping
("/getStatisticsOrStartCreator")
public Statistics getStatisticsOrStartCreator(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "filter") ResultFilter filter,@RequestParam(name = "useEagerLoading") boolean useEagerLoading,@RequestParam(name = "allanswers") boolean allanswers,@RequestParam(name = "asynchronous") boolean asynchronous){
  return answerservice.getStatisticsOrStartCreator(survey,filter,useEagerLoading,allanswers,asynchronous);
}


@GetMapping
("/getByInvitationCode")
public AnswerSet getByInvitationCode(@RequestParam(name = "invitationId") String invitationId){
  return answerservice.getByInvitationCode(invitationId);
}


@GetMapping
("/getUploadedFilesForAnswerset")
public List<File> getUploadedFilesForAnswerset(@RequestParam(name = "answersetId") int answersetId){
  return answerservice.getUploadedFilesForAnswerset(answersetId);
}


@GetMapping
("/getFilesForQuestion")
public List<String[]> getFilesForQuestion(@RequestParam(name = "uid") String uid,@RequestParam(name = "draft") boolean draft){
  return answerservice.getFilesForQuestion(uid,draft);
}


@GetMapping
("/deleteOldDrafts")
public int deleteOldDrafts(@RequestParam(name = "date") Date date){
  return answerservice.deleteOldDrafts(date);
}


@GetMapping
("/deleteInvalidStatistics")
public int deleteInvalidStatistics(){
  return answerservice.deleteInvalidStatistics();
}


@GetMapping
("/getAllAnswers")
public List<AnswerSet> getAllAnswers(@RequestParam(name = "surveyId") int surveyId,@RequestParam(name = "filter") ResultFilter filter){
  return answerservice.getAllAnswers(surveyId,filter);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "s") Statistics s){
answerservice.save(s);
}


@GetMapping
("/getSql")
public String getSql(@RequestParam(name = "prefix") String prefix,@RequestParam(name = "surveyId") int surveyId,@RequestParam(name = "filter") ResultFilter filter,@RequestParam(name = "values") Map<String,Object> values,@RequestParam(name = "searchallsurveys") boolean searchallsurveys){
  return answerservice.getSql(prefix,surveyId,filter,values,searchallsurveys);
}


@PutMapping
("/saveDraft")
public void saveDraft(@RequestParam(name = "draft") Draft draft){
answerservice.saveDraft(draft);
}


}