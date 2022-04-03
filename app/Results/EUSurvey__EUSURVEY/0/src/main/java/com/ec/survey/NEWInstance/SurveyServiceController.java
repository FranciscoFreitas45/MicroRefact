package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SurveyServiceController {

 private SurveyService surveyservice;


@GetMapping
("/editSave")
public Survey editSave(@RequestParam(name = "oldsurvey") Survey oldsurvey,@RequestParam(name = "request") HttpServletRequest request){
  return surveyservice.editSave(oldsurvey,request);
}


@GetMapping
("/answerSetExists")
public boolean answerSetExists(@RequestParam(name = "uniqueCode") String uniqueCode,@RequestParam(name = "isDraft") boolean isDraft,@RequestParam(name = "addErrorIfExists") boolean addErrorIfExists){
  return surveyservice.answerSetExists(uniqueCode,isDraft,addErrorIfExists);
}


@PutMapping
("/removeFromSessionCache")
public void removeFromSessionCache(@RequestParam(name = "survey") Survey survey){
surveyservice.removeFromSessionCache(survey);
}


@GetMapping
("/getLanguages")
public List<Language> getLanguages(){
  return surveyservice.getLanguages();
}


@GetMapping
("/getSurveyByUniqueId")
public Survey getSurveyByUniqueId(@RequestParam(name = "uid") String uid,@RequestParam(name = "loadTranslations") boolean loadTranslations,@RequestParam(name = "draft") boolean draft){
  return surveyservice.getSurveyByUniqueId(uid,loadTranslations,draft);
}


@GetMapping
("/getSurvey")
public Survey getSurvey(@RequestParam(name = "uidorshortname") String uidorshortname,@RequestParam(name = "isDraft") boolean isDraft,@RequestParam(name = "checkActive") boolean checkActive,@RequestParam(name = "readReplies") boolean readReplies,@RequestParam(name = "useEagerLoading") boolean useEagerLoading,@RequestParam(name = "language") String language,@RequestParam(name = "readonly") boolean readonly,@RequestParam(name = "checkNotDeleted") boolean checkNotDeleted,@RequestParam(name = "shortnamefirst") boolean shortnamefirst,@RequestParam(name = "synchronize") boolean synchronize){
  return surveyservice.getSurvey(uidorshortname,isDraft,checkActive,readReplies,useEagerLoading,language,readonly,checkNotDeleted,shortnamefirst,synchronize);
}


@PutMapping
("/markAsArchived")
public void markAsArchived(@RequestParam(name = "uid") String uid){
surveyservice.markAsArchived(uid);
}


@GetMapping
("/exportSurvey")
public java.io.File exportSurvey(@RequestParam(name = "shortname") String shortname,@RequestParam(name = "surveyService") SurveyService surveyService,@RequestParam(name = "answers") boolean answers){
  return surveyservice.exportSurvey(shortname,surveyService,answers);
}


@PutMapping
("/deleteNoTransaction")
public void deleteNoTransaction(@RequestParam(name = "id") int id,@RequestParam(name = "deleteLogs") boolean deleteLogs,@RequestParam(name = "deleteFileMappings") boolean deleteFileMappings){
surveyservice.deleteNoTransaction(id,deleteLogs,deleteFileMappings);
}


@GetMapping
("/getSurveysForUser")
public List<Integer> getSurveysForUser(@RequestParam(name = "userid") int userid){
  return surveyservice.getSurveysForUser(userid);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "survey") Survey survey){
surveyservice.delete(survey);
}


@GetMapping
("/getSurveysWithPrivilegesForUser")
public List<Integer> getSurveysWithPrivilegesForUser(@RequestParam(name = "userid") int userid){
  return surveyservice.getSurveysWithPrivilegesForUser(userid);
}


@GetMapping
("/getAccesses")
public List<Access> getAccesses(@RequestParam(name = "id") Integer id){
  return surveyservice.getAccesses(id);
}


@PutMapping
("/deleteAccess")
public void deleteAccess(@RequestParam(name = "access") Access access){
surveyservice.deleteAccess(access);
}


@GetMapping
("/getSurveysMarkedDeleted")
public List<Integer> getSurveysMarkedDeleted(){
  return surveyservice.getSurveysMarkedDeleted();
}


@PutMapping
("/sendAbuseReportsMailForYesterday")
public void sendAbuseReportsMailForYesterday(){
surveyservice.sendAbuseReportsMailForYesterday();
}


@GetMapping
("/getAllSurveyUIDs")
public List<String> getAllSurveyUIDs(@RequestParam(name = "onlypublished") boolean onlypublished){
  return surveyservice.getAllSurveyUIDs(onlypublished);
}


@PutMapping
("/initializeSurvey")
public void initializeSurvey(@RequestParam(name = "survey") Survey survey){
surveyservice.initializeSurvey(survey);
}


@PutMapping
("/checkAndRecreateMissingElements")
public void checkAndRecreateMissingElements(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "filter") ResultFilter filter){
surveyservice.checkAndRecreateMissingElements(survey,filter);
}


@GetMapping
("/getUniqueIdsById")
public Map<Integer,String> getUniqueIdsById(@RequestParam(name = "publishedSurvey") Survey publishedSurvey){
  return surveyservice.getUniqueIdsById(publishedSurvey);
}


@PutMapping
("/saveLanguages")
public void saveLanguages(@RequestParam(name = "langs") List<Language> langs){
surveyservice.saveLanguages(langs);
}


@PutMapping
("/createStatus")
public void createStatus(@RequestParam(name = "version") int version){
surveyservice.createStatus(version);
}


@GetMapping
("/add")
public Survey add(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "synchronize") boolean synchronize,@RequestParam(name = "userId") int userId){
  return surveyservice.add(survey,synchronize,userId);
}


@GetMapping
("/publish")
public Survey publish(@RequestParam(name = "draftSurvey") Survey draftSurvey,@RequestParam(name = "pnumberOfAnswerSets") int pnumberOfAnswerSets,@RequestParam(name = "pnumberOfAnswerSetsPublished") int pnumberOfAnswerSetsPublished,@RequestParam(name = "deactivateAutoPublishing") boolean deactivateAutoPublishing,@RequestParam(name = "userId") int userId,@RequestParam(name = "resetSourceIds") boolean resetSourceIds,@RequestParam(name = "resetSurvey") boolean resetSurvey){
  return surveyservice.publish(draftSurvey,pnumberOfAnswerSets,pnumberOfAnswerSetsPublished,deactivateAutoPublishing,userId,resetSourceIds,resetSurvey);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "objLang") Language objLang){
surveyservice.save(objLang);
}


@PutMapping
("/removeDependencies")
public void removeDependencies(@RequestParam(name = "element") Element element){
surveyservice.removeDependencies(element);
}


@GetMapping
("/repairXHTML")
public int repairXHTML(@RequestParam(name = "translations") Translations translations,@RequestParam(name = "builder") DocumentBuilder builder){
  return surveyservice.repairXHTML(translations,builder);
}


@GetMapping
("/importSurvey")
public int importSurvey(@RequestParam(name = "result") ImportResult result,@RequestParam(name = "user") User user,@RequestParam(name = "isRestore") boolean isRestore){
  return surveyservice.importSurvey(result,user,isRestore);
}


@GetMapping
("/activate")
public Survey activate(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "deactivateAutoPublishing") boolean deactivateAutoPublishing,@RequestParam(name = "userId") int userId){
  return surveyservice.activate(survey,deactivateAutoPublishing,userId);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "element") Element element){
surveyservice.update(element);
}


}