package edu.nju.careerbridge.youth.bean;
 public class JobExperienceBean {

 private  String phone;

 private  String companyName;

 private  String companyQuality;

 private  String companyLevel;

 private  String job;

 private  String fromTime;

 private  String toTime;

 private  String description;

public JobExperienceBean() {
}public JobExperienceBean(String phone, String companyName, String companyQuality, String companyLevel, String job, String fromTime, String toTime, String description) {
    this.phone = phone;
    this.companyName = companyName;
    this.companyQuality = companyQuality;
    this.companyLevel = companyLevel;
    this.job = job;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.description = description;
}
public String getPhone(){
    return phone;
}


public String getCompanyLevel(){
    return companyLevel;
}


public void setCompanyQuality(String companyQuality){
    this.companyQuality = companyQuality;
}


public String getFromTime(){
    return fromTime;
}


public void setToTime(String toTime){
    this.toTime = toTime;
}


public String getCompanyQuality(){
    return companyQuality;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setDescription(String description){
    this.description = description;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public String getDescription(){
    return description;
}


public void setCompanyLevel(String companyLevel){
    this.companyLevel = companyLevel;
}


public String getJob(){
    return job;
}


public String getToTime(){
    return toTime;
}


public String getCompanyName(){
    return companyName;
}


public void setJob(String job){
    this.job = job;
}


@Override
public String toString(){
    return "JobExperienceBean{" + "phone='" + phone + '\'' + ", companyName='" + companyName + '\'' + ", companyQuality='" + companyQuality + '\'' + ", companyLevel='" + companyLevel + '\'' + ", job='" + job + '\'' + ", fromTime='" + fromTime + '\'' + ", toTime='" + toTime + '\'' + ", description='" + description + '\'' + '}';
}


public void setFromTime(String fromTime){
    this.fromTime = fromTime;
}


}