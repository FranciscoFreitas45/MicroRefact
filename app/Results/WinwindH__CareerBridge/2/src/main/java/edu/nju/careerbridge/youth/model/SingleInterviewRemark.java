package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "single_interview_remark")
public class SingleInterviewRemark {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String phone;

 private  String company;

 private  String result;

 private  Integer difficulty;

 private  Integer feeling;

 private  String remark;

public SingleInterviewRemark() {
}public SingleInterviewRemark(String phone, String company, String result, Integer difficulty, Integer feeling, String remark) {
    this.phone = phone;
    this.company = company;
    this.result = result;
    this.difficulty = difficulty;
    this.feeling = feeling;
    this.remark = remark;
}
public String getPhone(){
    return phone;
}


public Integer getFeeling(){
    return feeling;
}


public void setResult(String result){
    this.result = result;
}


public Integer getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setDifficulty(Integer difficulty){
    this.difficulty = difficulty;
}


public void setFeeling(Integer feeling){
    this.feeling = feeling;
}


public String getCompany(){
    return company;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getResult(){
    return result;
}


public String getRemark(){
    return remark;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getDifficulty(){
    return difficulty;
}


public void setCompany(String company){
    this.company = company;
}


}