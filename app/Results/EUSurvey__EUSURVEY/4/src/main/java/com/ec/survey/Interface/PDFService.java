package com.ec.survey.Interface;
public interface PDFService {

   public java.io.File createAnswerPDF(Integer answerSetId,String uniqueCode,String surveyUid,boolean isDraft);
}