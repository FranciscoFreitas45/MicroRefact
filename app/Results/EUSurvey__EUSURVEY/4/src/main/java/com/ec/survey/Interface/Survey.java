package com.ec.survey.Interface;
public interface Survey {

   public String getShortname();
   public Integer getId();
   public Publication getPublication();
   public String getUniqueId();
   public String cleanTitle();
   public String cleanTitleForMailSubject();
}