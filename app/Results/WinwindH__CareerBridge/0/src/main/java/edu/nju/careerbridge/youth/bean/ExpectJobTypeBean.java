package edu.nju.careerbridge.youth.bean;
 public class ExpectJobTypeBean {

 private  String phone;

 private  String expectJobType;

public ExpectJobTypeBean() {
}public ExpectJobTypeBean(String phone, String expectJobType) {
    this.phone = phone;
    this.expectJobType = expectJobType;
}
public String getPhone(){
    return phone;
}


public void setExpectJobType(String expectJobType){
    this.expectJobType = expectJobType;
}


public String getExpectJobType(){
    return expectJobType;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Override
public String toString(){
    return "ExpectJobTypeBean{" + "phone='" + phone + '\'' + ", expectJobType='" + expectJobType + '\'' + '}';
}


}