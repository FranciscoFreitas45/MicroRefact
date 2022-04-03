package edu.nju.careerbridge.DTO;
 import javax.persistence;
public class Skill {

 private  Integer id;

 private  String phone;

 private  String skillName;

 private  String degree;

 private  String certificate;

 private  String description;

public Skill() {
}public Skill(String phone, String skillName, String degree, String certificate, String description) {
    this.phone = phone;
    this.skillName = skillName;
    this.degree = degree;
    this.certificate = certificate;
    this.description = description;
}
public String getPhone(){
    return phone;
}


public String getCertificate(){
    return certificate;
}


public void setId(Integer id){
    this.id = id;
}


public String getDegree(){
    return degree;
}


public Integer getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getSkillName(){
    return skillName;
}


}