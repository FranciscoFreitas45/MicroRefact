package com.ec.survey.Interface;
public interface PDFService {

   public java.io.File createSurveyPDF(Survey survey,String lang,java.io.File target);
}