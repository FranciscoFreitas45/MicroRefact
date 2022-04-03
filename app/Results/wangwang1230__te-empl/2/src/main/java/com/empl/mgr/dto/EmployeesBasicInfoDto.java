package com.empl.mgr.dto;
 import java.io.Serializable;
public class EmployeesBasicInfoDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String photo;

 private  String name;

 private  boolean sex;

 private  String identity;

 private  String birthday;

 private  String participateTime;

 private  String phone;

 private  String socialSecurity;

 private  long deparemtn;

 private  long position;

 private  long education;

 private  long marriage;

 private  long politics;

 private  long national;

 private  long current;

 private  long register;

 private  int state;

 private  String contact;

 private  String emergencyContact;

 private  String emergencyPhone;

 private  String school;

 private  String professional;

 private  String graduationTime;

 private  String schooling;

 private  String degree;

 private  boolean isSocialSecurity;

 private  String note;

public EmployeesBasicInfoDto() {
// TODO Auto-generated constructor stub
}
public void setGraduationTime(String graduationTime){
    this.graduationTime = graduationTime;
}


public String getName(){
    return name;
}


public String getPhoto(){
    return photo;
}


public String getProfessional(){
    return professional;
}


public String getEmergencyContact(){
    return emergencyContact;
}


public void setEducation(long education){
    this.education = education;
}


public void setPosition(long position){
    this.position = position;
}


public String getSchool(){
    return school;
}


public boolean getIsSocialSecurity(){
    return isSocialSecurity;
}


public void setId(long id){
    this.id = id;
}


public boolean isSex(){
    return sex;
}


public void setSex(boolean sex){
    this.sex = sex;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setContact(String contact){
    this.contact = contact;
}


public long getPolitics(){
    return politics;
}


public void setBirthday(String birthday){
    this.birthday = birthday;
}


public long getRegister(){
    return register;
}


public long getMarriage(){
    return marriage;
}


public String getGraduationTime(){
    return graduationTime;
}


public String getSchooling(){
    return schooling;
}


public String getDegree(){
    return degree;
}


public void setNote(String note){
    this.note = note;
}


public void setIdentity(String identity){
    this.identity = identity;
}


public long getCurrent(){
    return current;
}


public void setName(String name){
    this.name = name;
}


public String getPhone(){
    return phone;
}


public long getId(){
    return id;
}


public void setNational(long national){
    this.national = national;
}


public void setRegister(long register){
    this.register = register;
}


public void setProfessional(String professional){
    this.professional = professional;
}


public void setParticipateTime(String participateTime){
    this.participateTime = participateTime;
}


public void setSocialSecurity(boolean isSocialSecurity){
    this.isSocialSecurity = isSocialSecurity;
}


public void setPolitics(long politics){
    this.politics = politics;
}


public long getDeparemtn(){
    return deparemtn;
}


public void setSchool(String school){
    this.school = school;
}


public void setCurrent(long current){
    this.current = current;
}


public void setMarriage(long marriage){
    this.marriage = marriage;
}


public String getBirthday(){
    return birthday;
}


public String getParticipateTime(){
    return participateTime;
}


public void setDeparemtn(long deparemtn){
    this.deparemtn = deparemtn;
}


public String getSocialSecurity(){
    return socialSecurity;
}


public long getEducation(){
    return education;
}


public String getEmergencyPhone(){
    return emergencyPhone;
}


public String getNote(){
    return note;
}


public long getNational(){
    return national;
}


public String getContact(){
    return contact;
}


public void setEmergencyContact(String emergencyContact){
    this.emergencyContact = emergencyContact;
}


public void setEmergencyPhone(String emergencyPhone){
    this.emergencyPhone = emergencyPhone;
}


public long getPosition(){
    return position;
}


public int getState(){
    return state;
}


public void setPhoto(String photo){
    this.photo = photo;
}


public String getIdentity(){
    return identity;
}


public void setSchooling(String schooling){
    this.schooling = schooling;
}


public void setState(int state){
    this.state = state;
}


@Override
public String toString(){
    return "EmployeesBasicInfoDto [id:" + id + ", photo:" + photo + ", name:" + name + ", sex:" + sex + ", identity:" + identity + ", birthday:" + birthday + ", participateTime:" + participateTime + ", phone:" + phone + ", socialSecurity:" + socialSecurity + ", deparemtn:" + deparemtn + ", position:" + position + ", education:" + education + ", marriage:" + marriage + ", politics:" + politics + ", national:" + national + ", current:" + current + ", register:" + register + ", contact:" + contact + ", emergencyContact:" + emergencyContact + ", emergencyPhone:" + emergencyPhone + ", school:" + school + ", professional:" + professional + ", graduationTime:" + graduationTime + ", schooling:" + schooling + ", degree:" + degree + ", isSocialSecurity:" + isSocialSecurity + ", note:" + note + "]";
}


public void setDegree(String degree){
    this.degree = degree;
}


}