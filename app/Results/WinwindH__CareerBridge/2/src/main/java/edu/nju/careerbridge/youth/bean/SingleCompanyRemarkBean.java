package edu.nju.careerbridge.youth.bean;
 public class SingleCompanyRemarkBean {

 private  String phone;

 private  String company;

 private  int recomandScore;

 private  int futureScore;

 private  int ceoScore;

 private  String remark;

public SingleCompanyRemarkBean() {
}public SingleCompanyRemarkBean(String phone, String company, int recomandScore, int futureScore, int ceoScore, String remark) {
    this.phone = phone;
    this.company = company;
    this.recomandScore = recomandScore;
    this.futureScore = futureScore;
    this.ceoScore = ceoScore;
    this.remark = remark;
}
public String getPhone(){
    return phone;
}


public void setCeoScore(int ceoScore){
    this.ceoScore = ceoScore;
}


public void setPhone(String phone){
    this.phone = phone;
}


public int getFutureScore(){
    return futureScore;
}


public int getRecomandScore(){
    return recomandScore;
}


public void setRecomandScore(int recomandScore){
    this.recomandScore = recomandScore;
}


public String getCompany(){
    return company;
}


public int getCeoScore(){
    return ceoScore;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setFutureScore(int futureScore){
    this.futureScore = futureScore;
}


@Override
public String toString(){
    return "SingleCompanyRemarkBean{" + "phone='" + phone + '\'' + ", company='" + company + '\'' + ", recomandScore=" + recomandScore + ", futureScore=" + futureScore + ", ceoScore=" + ceoScore + ", remark='" + remark + '\'' + '}';
}


public void setCompany(String company){
    this.company = company;
}


}