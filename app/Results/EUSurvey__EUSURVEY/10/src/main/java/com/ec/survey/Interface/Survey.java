package com.ec.survey.Interface;
public interface Survey {

   public void computeTriggers();
   public Language getLanguage();
   public Map<Integer,Question> getQuestionMap();
   public Object get(Object Object);
   public Map<Integer,Element> getMatrixMap();
   public Map<String,Element> getQuestionMapByUniqueId();
   public List<Element> getElements();
   public Map<Integer,Element> getMissingElementsById();
   public Object containsKey(Object Object);
   public String getShortname();
   public boolean getMultiPaging();
   public int getSectionNumbering();
   public int getQuestionNumbering();
   public Boolean getWcagCompliance();
}