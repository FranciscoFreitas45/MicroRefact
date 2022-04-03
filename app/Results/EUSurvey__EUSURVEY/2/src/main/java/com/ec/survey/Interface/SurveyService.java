package com.ec.survey.Interface;
public interface SurveyService {

   public Survey editSave(Survey oldsurvey,HttpServletRequest request);
   public boolean answerSetExists(String uniqueCode,boolean isDraft,boolean addErrorIfExists);
   public void removeFromSessionCache(Survey survey);
}