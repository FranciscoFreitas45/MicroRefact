package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "education")
public class Education {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String phone;

 private  String educationDegree;

 private  String school;

 private  String major;

 private  String fromTime;

 private  String toTime;

public Education() {
}public Education(String phone, String educationDegree, String school, String major, String fromTime, String toTime) {
    this.phone = phone;
    this.educationDegree = educationDegree;
    this.school = school;
    this.major = major;
    this.fromTime = fromTime;
    this.toTime = toTime;
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


public Integer getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setMajor(String major){
    this.major = major;
}


public String getSchool(){
    return school;
}


public String getToTime(){
    return toTime;
}


public void setId(Integer id){
    this.id = id;
}


public String getEducationDegree(){
    return educationDegree;
}


public void setSchool(String school){
    this.school = school;
}


public void setFromTime(String fromTime){
    this.fromTime = fromTime;
}


}