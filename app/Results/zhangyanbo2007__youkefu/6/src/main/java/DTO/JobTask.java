package DTO;
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


public String getDicids(){
    return dicids;
}


public String[] getRunTypes(){
    return runTypes;
}


public Integer getRunBeginHour(){
    return runBeginHour;
}


public Boolean getIsRepeat(){
    return isRepeat != null ? isRepeat : false;
}


public String[] getRunDates(){
    return runDates;
}


public String[] getFormats(){
    return formats;
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


public String getEmails(){
    return emails;
}


public Integer getRepeatSpace(){
    return repeatSpace;
}


public String[] getDatetypes(){
    return datetypes;
}


public Integer getRunBeginSecond(){
    return runBeginSecond;
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


public String getExcelType(){
    return excelType;
}


public int getPriority(){
    return priority;
}


public Integer getRunBeginDate(){
    return runBeginDate;
}


public Integer getRunBeginMinute(){
    return runBeginMinute;
}


public String getParams(){
    return params;
}


}