package edu.nju.careerbridge.youth.bean;
 public class ExpectCompanyLevelBean {

 private  String phone;

 private  String expectCompanyLevel;

public ExpectCompanyLevelBean() {
}public ExpectCompanyLevelBean(String phone, String expectCompanyLevel) {
    this.phone = phone;
    this.expectCompanyLevel = expectCompanyLevel;
}
public String getPhone(){
    return phone;
}


public String getExpectCompanyLevel(){
    return expectCompanyLevel;
}


public void setExpectCompanyLevel(String expectCompanyLevel){
    this.expectCompanyLevel = expectCompanyLevel;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Override
public String toString(){
    return "ExpectCompanyLevelBean{" + "phone='" + phone + '\'' + ", expectCompanyLevel='" + expectCompanyLevel + '\'' + '}';
}


}