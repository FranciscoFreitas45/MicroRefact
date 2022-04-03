package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SurveyController {

 private Survey survey;


@GetMapping
("/getLanguage")
public Language getLanguage(){
  return survey.getLanguage();
}


@GetMapping
("/getShortname")
public String getShortname(){
  return survey.getShortname();
}


@GetMapping
("/getId")
public Integer getId(){
  return survey.getId();
}


@GetMapping
("/getElementsRecursive")
public List<Element> getElementsRecursive(@RequestParam(name = "answers") boolean answers){
  return survey.getElementsRecursive(answers);
}


@GetMapping
("/getUniqueId")
public String getUniqueId(){
  return survey.getUniqueId();
}


@GetMapping
("/getPublication")
public Publication getPublication(){
  return survey.getPublication();
}


@GetMapping
("/cleanTitle")
public String cleanTitle(){
  return survey.cleanTitle();
}


@GetMapping
("/cleanTitleForMailSubject")
public String cleanTitleForMailSubject(){
  return survey.cleanTitleForMailSubject();
}


@GetMapping
("/isMissingElementsChecked")
public boolean isMissingElementsChecked(){
  return survey.isMissingElementsChecked();
}


@PutMapping
("/setNumberOfAnswerSets")
public void setNumberOfAnswerSets(@RequestParam(name = "numberOfAnswerSets") int numberOfAnswerSets){
survey.setNumberOfAnswerSets(numberOfAnswerSets);
}


@GetMapping
("/getIsQuiz")
public Boolean getIsQuiz(){
  return survey.getIsQuiz();
}


@GetMapping
("/getNumberOfAnswerSets")
public int getNumberOfAnswerSets(){
  return survey.getNumberOfAnswerSets();
}


@GetMapping
("/getQuestions")
public List<Question> getQuestions(){
  return survey.getQuestions();
}


@GetMapping
("/getMissingElements")
public List<Element> getMissingElements(){
  return survey.getMissingElements();
}


@GetMapping
("/getIsDelphi")
public Boolean getIsDelphi(){
  return survey.getIsDelphi();
}


@GetMapping
("/getIsDraft")
public boolean getIsDraft(){
  return survey.getIsDraft();
}


@PutMapping
("/computeTriggers")
public void computeTriggers(){
survey.computeTriggers();
}


@GetMapping
("/getQuestionMap")
public Map<Integer,Question> getQuestionMap(){
  return survey.getQuestionMap();
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return survey.get(Object);
}


@GetMapping
("/getMatrixMap")
public Map<Integer,Element> getMatrixMap(){
  return survey.getMatrixMap();
}


@GetMapping
("/getQuestionMapByUniqueId")
public Map<String,Element> getQuestionMapByUniqueId(){
  return survey.getQuestionMapByUniqueId();
}


@GetMapping
("/getElements")
public List<Element> getElements(){
  return survey.getElements();
}


@GetMapping
("/getMissingElementsById")
public Map<Integer,Element> getMissingElementsById(){
  return survey.getMissingElementsById();
}


@GetMapping
("/containsKey")
public Object containsKey(@RequestParam(name = "Object") Object Object){
  return survey.containsKey(Object);
}


@GetMapping
("/getMultiPaging")
public boolean getMultiPaging(){
  return survey.getMultiPaging();
}


@GetMapping
("/getSectionNumbering")
public int getSectionNumbering(){
  return survey.getSectionNumbering();
}


@GetMapping
("/getQuestionNumbering")
public int getQuestionNumbering(){
  return survey.getQuestionNumbering();
}


@GetMapping
("/getWcagCompliance")
public Boolean getWcagCompliance(){
  return survey.getWcagCompliance();
}


@PutMapping
("/setEnd")
public void setEnd(@RequestParam(name = "end") Date end){
survey.setEnd(end);
}


@GetMapping
("/isAnonymous")
public boolean isAnonymous(){
  return survey.isAnonymous();
}


@PutMapping
("/setTranslations")
public void setTranslations(@RequestParam(name = "translations") List<String> translations){
survey.setTranslations(translations);
}


@PutMapping
("/setShortname")
public void setShortname(@RequestParam(name = "shortname") String shortname){
survey.setShortname(shortname);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
survey.setTitle(title);
}


@PutMapping
("/setContact")
public void setContact(@RequestParam(name = "contact") String contact){
survey.setContact(contact);
}


@PutMapping
("/setStart")
public void setStart(@RequestParam(name = "start") Date start){
survey.setStart(start);
}


@PutMapping
("/setAutomaticPublishing")
public void setAutomaticPublishing(@RequestParam(name = "automaticPublishing") boolean automaticPublishing){
survey.setAutomaticPublishing(automaticPublishing);
}


@PutMapping
("/setIsActive")
public void setIsActive(@RequestParam(name = "isActive") Boolean isActive){
survey.setIsActive(isActive);
}


@PutMapping
("/setIsPublished")
public void setIsPublished(@RequestParam(name = "isPublished") boolean isPublished){
survey.setIsPublished(isPublished);
}


@PutMapping
("/setLogoText")
public void setLogoText(@RequestParam(name = "logoText") String logoText){
survey.setLogoText(logoText);
}


@PutMapping
("/setIntroduction")
public void setIntroduction(@RequestParam(name = "introduction") String introduction){
survey.setIntroduction(introduction);
}


@PutMapping
("/setEscapePage")
public void setEscapePage(@RequestParam(name = "escapePage") String escapePage){
survey.setEscapePage(escapePage);
}


@PutMapping
("/setEscapeLink")
public void setEscapeLink(@RequestParam(name = "escapeLink") String escapeLink){
survey.setEscapeLink(escapeLink);
}


@PutMapping
("/setConfirmationPage")
public void setConfirmationPage(@RequestParam(name = "confirmationPage") String confirmationPage){
survey.setConfirmationPage(confirmationPage);
}


@PutMapping
("/setConfirmationLink")
public void setConfirmationLink(@RequestParam(name = "confirmationLink") String confirmationLink){
survey.setConfirmationLink(confirmationLink);
}


@PutMapping
("/setLanguage")
public void setLanguage(@RequestParam(name = "language") Language language){
survey.setLanguage(language);
}


@PutMapping
("/setQuizWelcomeMessage")
public void setQuizWelcomeMessage(@RequestParam(name = "quizWelcomeMessage") String quizWelcomeMessage){
survey.setQuizWelcomeMessage(quizWelcomeMessage);
}


@PutMapping
("/setQuizResultsMessage")
public void setQuizResultsMessage(@RequestParam(name = "quizResultsMessage") String quizResultsMessage){
survey.setQuizResultsMessage(quizResultsMessage);
}


@GetMapping
("/serialize")
public String serialize(@RequestParam(name = "elementOrderOnly") boolean elementOrderOnly){
  return survey.serialize(elementOrderOnly);
}


@PutMapping
("/reorderElementsByPosition")
public void reorderElementsByPosition(){
survey.reorderElementsByPosition();
}


@PutMapping
("/resetElementsRecursive")
public void resetElementsRecursive(){
survey.resetElementsRecursive();
}


@PutMapping
("/setActivitiesToLog")
public void setActivitiesToLog(@RequestParam(name = "activitiesToLog") Map<Integer,String[]> activitiesToLog){
survey.setActivitiesToLog(activitiesToLog);
}


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
survey.setId(id);
}


@PutMapping
("/setOwner")
public void setOwner(@RequestParam(name = "owner") User owner){
survey.setOwner(owner);
}


@PutMapping
("/setUniqueId")
public void setUniqueId(@RequestParam(name = "uniqueId") String uniqueId){
survey.setUniqueId(uniqueId);
}


@PutMapping
("/setSecurity")
public void setSecurity(@RequestParam(name = "security") String security){
survey.setSecurity(security);
}


@PutMapping
("/setCaptcha")
public void setCaptcha(@RequestParam(name = "captcha") boolean captcha){
survey.setCaptcha(captcha);
}


@PutMapping
("/setSectionNumbering")
public void setSectionNumbering(@RequestParam(name = "sectionNumbering") int sectionNumbering){
survey.setSectionNumbering(sectionNumbering);
}


@PutMapping
("/setQuestionNumbering")
public void setQuestionNumbering(@RequestParam(name = "questionNumbering") int questionNumbering){
survey.setQuestionNumbering(questionNumbering);
}


@PutMapping
("/setListForm")
public void setListForm(@RequestParam(name = "listForm") boolean listForm){
survey.setListForm(listForm);
}


@PutMapping
("/setMultiPaging")
public void setMultiPaging(@RequestParam(name = "multiPaging") boolean multiPaging){
survey.setMultiPaging(multiPaging);
}


@PutMapping
("/setSkin")
public void setSkin(@RequestParam(name = "skin") Skin skin){
survey.setSkin(skin);
}


@PutMapping
("/setNumberOfAnswerSetsPublished")
public void setNumberOfAnswerSetsPublished(@RequestParam(name = "numberOfAnswerSetsPublished") int numberOfAnswerSetsPublished){
survey.setNumberOfAnswerSetsPublished(numberOfAnswerSetsPublished);
}


@PutMapping
("/setIsDelphi")
public void setIsDelphi(@RequestParam(name = "isDelphi") Boolean isDelphi){
survey.setIsDelphi(isDelphi);
}


@PutMapping
("/setLogo")
public void setLogo(@RequestParam(name = "logo") File logo){
survey.setLogo(logo);
}


@PutMapping
("/setIsDraft")
public void setIsDraft(@RequestParam(name = "draft") boolean draft){
survey.setIsDraft(draft);
}


@PutMapping
("/setCreated")
public void setCreated(@RequestParam(name = "created") Date created){
survey.setCreated(created);
}


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "password") String password){
survey.setPassword(password);
}


@PutMapping
("/setIsECF")
public void setIsECF(@RequestParam(name = "isECF") Boolean isECF){
survey.setIsECF(isECF);
}


}