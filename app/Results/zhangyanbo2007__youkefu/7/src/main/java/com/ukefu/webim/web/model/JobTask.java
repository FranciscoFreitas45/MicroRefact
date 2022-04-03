package com.ukefu.webim.web.model;
 public class JobTask {

 private  long serialVersionUID;

 private  String[] reportids;

 private  String emails;

 private  String[] runDates;

 private  String[] formats;

 private  String[] runTypes;

 private  String[] datetypes;

 private  String excelType;

 private  String emailType;

 private  String emailTitle;

 private  String emailContent;

 private  int priority;

 private  Integer runBeginDate;

 private  String runCycle;

 private  Integer runSpace;

 private  Integer runBeginHour;

 private  Integer runBeginMinute;

 private  Integer runBeginSecond;

 private  Boolean isRepeat;

 private  Integer repeatSpace;

 private  Integer repeatJustTime;

 private  String dicids;

 private  String params;


public void setEmailTitle(String emailTitle){
    this.emailTitle = emailTitle;
}


public String getDicids(){
    return dicids;
}


public String[] getRunTypes(){
    return runTypes;
}


public void setRunBeginHour(Integer runBeginHour){
    this.runBeginHour = runBeginHour;
}


public void setExcelType(String excelType){
    this.excelType = excelType;
}


public Integer getRunBeginHour(){
    return runBeginHour;
}


public void setFormats(String[] formats){
    this.formats = formats;
}


public Boolean getIsRepeat(){
    return isRepeat != null ? isRepeat : false;
}


public void setEmails(String emails){
    this.emails = emails;
}


public String[] getRunDates(){
    return runDates;
}


public String[] getFormats(){
    return formats;
}


public void setRunBeginDate(Integer runBeginDate){
    this.runBeginDate = runBeginDate;
}


public void setRunDates(String[] runDates){
    this.runDates = runDates;
}


public String getEmailContent(){
    return emailContent;
}


public String[] getReportids(){
    return reportids;
}


public String getEmailType(){
    return emailType;
}


public void setEmailType(String emailType){
    this.emailType = emailType;
}


public String getEmails(){
    return emails;
}


public Integer getRepeatSpace(){
    return repeatSpace;
}


public String[] getDatetypes(){
    return datetypes;
}


public void setDicids(String dicids){
    this.dicids = dicids;
}


public Integer getRunBeginSecond(){
    return runBeginSecond;
}


public void setRunBeginSecond(Integer runBeginSecond){
    this.runBeginSecond = runBeginSecond;
}


public void setDatetypes(String[] datetypes){
    this.datetypes = datetypes;
}


public void setRepeatSpace(Integer repeatSpace){
    this.repeatSpace = repeatSpace;
}


public Integer getRunSpace(){
    return runSpace;
}


public String getRunCycle(){
    return runCycle;
}


public Integer getRepeatJustTime(){
    return repeatJustTime;
}


public String getEmailTitle(){
    return emailTitle;
}


public void setIsRepeat(Boolean isRepeat){
    this.isRepeat = isRepeat;
}


public String getExcelType(){
    return excelType;
}


public void setRunTypes(String[] runTypes){
    this.runTypes = runTypes;
}


public void setRunSpace(Integer runSpace){
    this.runSpace = runSpace;
}


public void setRunCycle(String runCycle){
    this.runCycle = runCycle;
}


public void setRepeatJustTime(Integer repeatJustTime){
    this.repeatJustTime = repeatJustTime;
}


public void setReportids(String[] reportids){
    this.reportids = reportids;
}


public int getPriority(){
    return priority;
}


public Integer getRunBeginDate(){
    return runBeginDate;
}


public void setEmailContent(String emailContent){
    this.emailContent = emailContent;
}


public String toString(){
    return super.toString().toLowerCase();
}


public Integer getRunBeginMinute(){
    return runBeginMinute;
}


public void setPriority(int priority){
    this.priority = priority;
}


public void setRunBeginMinute(Integer runBeginMinute){
    this.runBeginMinute = runBeginMinute;
}


public String getParams(){
    return params;
}


public void setParams(String params){
    this.params = params;
}


}