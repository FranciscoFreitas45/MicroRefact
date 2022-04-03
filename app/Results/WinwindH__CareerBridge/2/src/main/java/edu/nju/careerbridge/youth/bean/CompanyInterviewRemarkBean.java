package edu.nju.careerbridge.youth.bean;
 import java.util.List;
public class CompanyInterviewRemarkBean {

 private  String company;

 private  Double avgDifficulty;

 private  Double avgFeeling;

 private  List<SingleInterviewRemarkBean> singleInterviewRemarkBeans;

public CompanyInterviewRemarkBean() {
}public CompanyInterviewRemarkBean(String company, Double avgDifficulty, Double avgFeeling, List<SingleInterviewRemarkBean> singleInterviewRemarkBeans) {
    this.company = company;
    this.avgDifficulty = avgDifficulty;
    this.avgFeeling = avgFeeling;
    this.singleInterviewRemarkBeans = singleInterviewRemarkBeans;
}
public void setAvgFeeling(Double avgFeeling){
    this.avgFeeling = avgFeeling;
}


public List<SingleInterviewRemarkBean> getSingleInterviewRemarkBeans(){
    return singleInterviewRemarkBeans;
}


public void setSingleInterviewRemarkBeans(List<SingleInterviewRemarkBean> singleInterviewRemarkBeans){
    this.singleInterviewRemarkBeans = singleInterviewRemarkBeans;
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


@Override
public String toString(){
    return "CompanyInterviewRemarkBean{" + "company='" + company + '\'' + ", avgDifficulty=" + avgDifficulty + ", avgFeeling=" + avgFeeling + ", singleInterviewRemarkBeans=" + singleInterviewRemarkBeans + '}';
}


public void setCompany(String company){
    this.company = company;
}


public Double getAvgDifficulty(){
    return avgDifficulty;
}


}