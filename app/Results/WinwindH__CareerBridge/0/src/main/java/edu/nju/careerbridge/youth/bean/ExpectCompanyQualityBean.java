package edu.nju.careerbridge.youth.bean;
 public class ExpectCompanyQualityBean {

 private  String phone;

 private  String expectCompanyQuality;

public ExpectCompanyQualityBean() {
}public ExpectCompanyQualityBean(String phone, String expectCompanyQuality) {
    this.phone = phone;
    this.expectCompanyQuality = expectCompanyQuality;
}
public String getPhone(){
    return phone;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Override
public String toString(){
    return "ExpectCompanyQualityBean{" + "phone='" + phone + '\'' + ", expectCompanyQuality='" + expectCompanyQuality + '\'' + '}';
}


public String getExpectCompanyQuality(){
    return expectCompanyQuality;
}


public void setExpectCompanyQuality(String expectCompanyQuality){
    this.expectCompanyQuality = expectCompanyQuality;
}


}