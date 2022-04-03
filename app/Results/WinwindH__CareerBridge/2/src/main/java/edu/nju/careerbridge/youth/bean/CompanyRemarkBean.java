package edu.nju.careerbridge.youth.bean;
 import java.util.ArrayList;
import java.util.List;
public class CompanyRemarkBean {

 private  String company;

 private  Double avgRecomandScore;

 private  Double avgFutureScore;

 private  Double avgCeoScore;

 private  List<SingleCompanyRemarkBean> singleCompanyRemarkBeans;

// 所以对公司的单个评价singleCompanyRemarkBean
// 属性应该有phone,company,recomandScore, futureScore, ceoScore, String remark
public CompanyRemarkBean() {
}public CompanyRemarkBean(String company, Double avgRecomandScore, Double avgFutureScore, Double avgCeoScore, ArrayList<SingleCompanyRemarkBean> singleCompanyRemarkBeans) {
    this.company = company;
    this.avgRecomandScore = avgRecomandScore;
    this.avgFutureScore = avgFutureScore;
    this.avgCeoScore = avgCeoScore;
    this.singleCompanyRemarkBeans = singleCompanyRemarkBeans;
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


public List<SingleCompanyRemarkBean> getSingleCompanyRemarkBeans(){
    return singleCompanyRemarkBeans;
}


@Override
public String toString(){
    return "CompanyRemarkBean{" + "company='" + company + '\'' + ", avgRecomandScore=" + avgRecomandScore + ", avgFutureScore=" + avgFutureScore + ", avgCeoScore=" + avgCeoScore + ", singleCompanyRemarkBeans=" + singleCompanyRemarkBeans + '}';
}


public void setCompany(String company){
    this.company = company;
}


public Double getAvgCeoScore(){
    return avgCeoScore;
}


public void setSingleCompanyRemarkBeans(List<SingleCompanyRemarkBean> singleCompanyRemarkBeans){
    this.singleCompanyRemarkBeans = singleCompanyRemarkBeans;
}


}