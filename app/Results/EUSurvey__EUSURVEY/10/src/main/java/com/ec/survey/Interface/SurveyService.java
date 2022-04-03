package com.ec.survey.Interface;
public interface SurveyService {

   public void initializeSurvey(Survey survey);
   public void checkAndRecreateMissingElements(Survey survey,ResultFilter filter);
   public Map<Integer,String> getUniqueIdsById(Survey publishedSurvey);
}