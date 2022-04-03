package com.ec.survey.Interface;
public interface SessionService {

   public void ClearUniqueCodeForForm(HttpServletRequest request,int surveyId);
   public void initializeProxy();
   public String getCaptchaText(HttpServletRequest request);
}