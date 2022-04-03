package com.empl.mgr.dto;
 import java.io.Serializable;
import java.util.Arrays;
public class EmployeesInfoDto implements Serializable{

 private  long serialVersionUID;

 private  long emplId;

 private  String fullName;

 private  String photo;

 private  boolean sex;

 private  String identity;

 private  String birthday;

 private  String participate;

 private  String socialSecurity;

 private  long deparemtn;

 private  long position;

 private  long education;

 private  long marriage;

 private  long politics;

 private  long national;

 private  String phone;

 private  String contact;

 private  String emergencyContact;

 private  String emergencyPhone;

 private  String school;

 private  String professional;

 private  String graduationTime;

 private  String schooling;

 private  String degree;

 private  boolean isPaySocialSecurity;

 private  String note;

 private  EmployeesAddressDto register;

 private  EmployeesAddressDto current;

 private  EmployeesCompnayDto[] company;

public EmployeesInfoDto() {
// TODO Auto-generated constructor stub
}public EmployeesInfoDto(long emplId, String fullName, String photo, boolean sex, String identity, String birthday, String participate, String socialSecurity, long deparemtn, long position, long education, long marriage, long politics, long national, String phone, String contact, String emergencyContact, String emergencyPhone, String school, String professional, String graduationTime, String schooling, String degree, boolean isPaySocialSecurity, String note, EmployeesAddressDto register, EmployeesAddressDto current, EmployeesCompnayDto[] company) {
    super();
    this.emplId = emplId;
    this.fullName = fullName;
    this.photo = photo;
    this.sex = sex;
    this.identity = identity;
    this.birthday = birthday;
    this.participate = participate;
    this.socialSecurity = socialSecurity;
    this.deparemtn = deparemtn;
    this.position = position;
    this.education = education;
    this.marriage = marriage;
    this.politics = politics;
    this.national = national;
    this.phone = phone;
    this.contact = contact;
    this.emergencyContact = emergencyContact;
    this.emergencyPhone = emergencyPhone;
    this.school = school;
    this.professional = professional;
    this.graduationTime = graduationTime;
    this.schooling = schooling;
    this.degree = degree;
    this.isPaySocialSecurity = isPaySocialSecurity;
    this.note = note;
    this.register = register;
    this.current = current;
    this.company = company;
}
public void setGraduationTime(String graduationTime){
    this.graduationTime = graduationTime;
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


public String getFullName(){
    return fullName;
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


public EmployeesAddressDto getRegister(){
    return register;
}


public void setBirthday(String birthday){
    this.birthday = birthday;
}


public EmployeesCompnayDto[] getCompany(){
    return company;
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


public EmployeesAddressDto getCurrent(){
    return current;
}


public void setEmplId(long emplId){
    this.emplId = emplId;
}


public void setIdentity(String identity){
    this.identity = identity;
}


public String getParticipate(){
    return participate;
}


public void setPaySocialSecurity(boolean isPaySocialSecurity){
    this.isPaySocialSecurity = isPaySocialSecurity;
}


public String getPhone(){
    return phone;
}


public void setNational(long national){
    this.national = national;
}


public void setRegister(EmployeesAddressDto register){
    this.register = register;
}


public void setProfessional(String professional){
    this.professional = professional;
}


public void setSocialSecurity(String socialSecurity){
    this.socialSecurity = socialSecurity;
}


public void setPolitics(long politics){
    this.politics = politics;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public long getDeparemtn(){
    return deparemtn;
}


public boolean isPaySocialSecurity(){
    return isPaySocialSecurity;
}


public void setSchool(String school){
    this.school = school;
}


public void setCurrent(EmployeesAddressDto current){
    this.current = current;
}


public void setCompany(EmployeesCompnayDto[] company){
    this.company = company;
}


public void setMarriage(long marriage){
    this.marriage = marriage;
}


public String getBirthday(){
    return birthday;
}


public void setParticipate(String participate){
    this.participate = participate;
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


public long getEmplId(){
    return emplId;
}


public void setEmergencyPhone(String emergencyPhone){
    this.emergencyPhone = emergencyPhone;
}


public long getPosition(){
    return position;
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


@Override
public String toString(){
    return "EmployeesInfoDto [emplId:" + emplId + ", fullName:" + fullName + ", photo:" + photo + ", sex:" + sex + ", identity:" + identity + ", birthday:" + birthday + ", participate:" + participate + ", socialSecurity:" + socialSecurity + ", deparemtn:" + deparemtn + ", position:" + position + ", education:" + education + ", marriage:" + marriage + ", politics:" + politics + ", national:" + national + ", phone:" + phone + ", contact:" + contact + ", emergencyContact:" + emergencyContact + ", emergencyPhone:" + emergencyPhone + ", school:" + school + ", professional:" + professional + ", graduationTime:" + graduationTime + ", schooling:" + schooling + ", degree:" + degree + ", isPaySocialSecurity:" + isPaySocialSecurity + ", note:" + note + ", register:" + register + ", current:" + current + ", company:" + Arrays.toString(company) + "]";
}


public void setDegree(String degree){
    this.degree = degree;
}


}