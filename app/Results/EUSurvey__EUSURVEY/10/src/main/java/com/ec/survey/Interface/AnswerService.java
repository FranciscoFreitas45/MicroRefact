package com.ec.survey.Interface;
public interface AnswerService {

   public List<AnswerSet> getAllAnswers(int surveyId,ResultFilter filter);
   public void save(Statistics s);
   public String getSql(String prefix,int surveyId,ResultFilter filter,Map<String,Object> values,boolean searchallsurveys);
}