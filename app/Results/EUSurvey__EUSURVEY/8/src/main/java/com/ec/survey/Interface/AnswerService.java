package com.ec.survey.Interface;
public interface AnswerService {

   public String serializeOriginal(Integer id);
   public void internalSaveAnswerSet(AnswerSet answerSet,String fileDir,String draftid,boolean invalidateExportsAndStatistics,boolean createAttendees);
   public int getNumberOfAnswerSetsPublished(String surveyname,String uid);
}