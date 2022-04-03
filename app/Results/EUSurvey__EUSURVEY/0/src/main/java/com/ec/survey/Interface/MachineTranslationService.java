package com.ec.survey.Interface;
public interface MachineTranslationService {

   public void saveSuccessResponse(String requestId,String targetLanguage,String translatedText);
   public void saveErrorResponse(String requestId,String targetLanguage,String errorCode,String errorMessage);
}