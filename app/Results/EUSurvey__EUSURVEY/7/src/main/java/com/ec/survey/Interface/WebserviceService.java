package com.ec.survey.Interface;
public interface WebserviceService {

   public ServiceRequest getServiceRequest(Integer userId);
   public void save(WebserviceTask task);
   public boolean startTask(WebserviceTask task,Locale locale);
   public void increaseServiceRequest(Integer userId);
   public int getWaitingTokens(ParticipationGroup group);
   public WebserviceTask get(int id);
}