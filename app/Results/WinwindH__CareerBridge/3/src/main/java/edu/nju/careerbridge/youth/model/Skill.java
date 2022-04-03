package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "skill")
public class Skill {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
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


public void setSkillName(String skillName){
    this.skillName = skillName;
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


public void setPhone(String phone){
    this.phone = phone;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setDegree(String degree){
    this.degree = degree;
}


public String getSkillName(){
    return skillName;
}


public void setCertificate(String certificate){
    this.certificate = certificate;
}


}