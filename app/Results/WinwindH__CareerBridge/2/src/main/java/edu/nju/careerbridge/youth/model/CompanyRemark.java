package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "company_remark")
public class CompanyRemark {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String company;

 private  Double avgRecomandScore;

 private  Double avgFutureScore;

 private  Double avgCeoScore;

public CompanyRemark() {
}public CompanyRemark(String company, Double avgRecomandScore, Double avgFutureScore, Double avgCeoScore) {
    this.company = company;
    this.avgRecomandScore = avgRecomandScore;
    this.avgFutureScore = avgFutureScore;
    this.avgCeoScore = avgCeoScore;
}
public void setAvgRecomandScore(Double avgRecomandScore){
    this.avgRecomandScore = avgRecomandScore;
}


public void setAvgCeoScore(Double avgCeoScore){
    this.avgCeoScore = avgCeoScore;
}


public String getCompany(){
    return company;
}


public void setAvgFutureScore(Double avgFutureScore){
    this.avgFutureScore = avgFutureScore;
}


public Double getAvgRecomandScore(){
    return avgRecomandScore;
}


public Double getAvgFutureScore(){
    return avgFutureScore;
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


public Double getAvgCeoScore(){
    return avgCeoScore;
}


}