package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "company_interview_remark")
public class CompanyInterviewRemark {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String company;

 private  Double avgDifficulty;

 private  Double avgFeeling;

public CompanyInterviewRemark() {
}public CompanyInterviewRemark(String company, Double avgDifficulty, Double avgFeeling) {
    this.company = company;
    this.avgDifficulty = avgDifficulty;
    this.avgFeeling = avgFeeling;
}
public void setAvgFeeling(Double avgFeeling){
    this.avgFeeling = avgFeeling;
}


public String getCompany(){
    return company;
}


public void setAvgDifficulty(Double avgDifficulty){
    this.avgDifficulty = avgDifficulty;
}


public Double getAvgFeeling(){
    return avgFeeling;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setCompany(String company){
    this.company = company;
}


public Double getAvgDifficulty(){
    return avgDifficulty;
}


}