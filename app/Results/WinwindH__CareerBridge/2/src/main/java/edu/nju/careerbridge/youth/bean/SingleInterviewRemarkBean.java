package edu.nju.careerbridge.youth.bean;
 public class SingleInterviewRemarkBean {

 private  String phone;

 private  String company;

 private  String result;

 private  Integer difficulty;

 private  Integer feeling;

 private  String remark;

public SingleInterviewRemarkBean() {
}public SingleInterviewRemarkBean(String phone, String company, String result, Integer difficulty, Integer feeling, String remark) {
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


@Override
public String toString(){
    return "SingleInterviewRemarkBean{" + "phone='" + phone + '\'' + ", company='" + company + '\'' + ", result='" + result + '\'' + ", difficulty=" + difficulty + ", feeling=" + feeling + ", remark='" + remark + '\'' + '}';
}


public Integer getDifficulty(){
    return difficulty;
}


public void setCompany(String company){
    this.company = company;
}


}