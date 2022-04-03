package edu.nju.careerbridge.youth.bean;
 import java.util.List;
public class EducationBean {

 private  String phone;

 private  String educationDegree;

 private  String school;

 private  String major;

 private  String fromTime;

 private  String toTime;

 private  List<HonorBean> honorBeans;

public EducationBean() {
}public EducationBean(String phone, String educationDegree, String school, String major, String fromTime, String toTime, List<HonorBean> honorBeans) {
    this.phone = phone;
    this.educationDegree = educationDegree;
    this.school = school;
    this.major = major;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.honorBeans = honorBeans;
}
public String getPhone(){
    return phone;
}


public String getFromTime(){
    return fromTime;
}


public void setEducationDegree(String educationDegree){
    this.educationDegree = educationDegree;
}


public String getMajor(){
    return major;
}


public void setToTime(String toTime){
    this.toTime = toTime;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setMajor(String major){
    this.major = major;
}


public void setHonorBeans(List<HonorBean> honorBeans){
    this.honorBeans = honorBeans;
}


public String getSchool(){
    return school;
}


public String getToTime(){
    return toTime;
}


public String getEducationDegree(){
    return educationDegree;
}


@Override
public String toString(){
    return "EducationBean{" + "phone='" + phone + '\'' + ", educationDegree='" + educationDegree + '\'' + ", school='" + school + '\'' + ", major='" + major + '\'' + ", fromTime='" + fromTime + '\'' + ", toTime='" + toTime + '\'' + ", honorBeans=" + honorBeans + '}';
}


public void setSchool(String school){
    this.school = school;
}


public void setFromTime(String fromTime){
    this.fromTime = fromTime;
}


public List<HonorBean> getHonorBeans(){
    return honorBeans;
}


}