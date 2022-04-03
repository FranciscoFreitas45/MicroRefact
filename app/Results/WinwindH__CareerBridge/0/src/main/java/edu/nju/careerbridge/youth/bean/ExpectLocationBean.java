package edu.nju.careerbridge.youth.bean;
 public class ExpectLocationBean {

 private  String phone;

 private  String expectLocation;

public ExpectLocationBean() {
}public ExpectLocationBean(String phone, String expectLocation) {
    this.phone = phone;
    this.expectLocation = expectLocation;
}
public String getPhone(){
    return phone;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Override
public String toString(){
    return "ExpectLocationBean{" + "phone='" + phone + '\'' + ", expectLocation='" + expectLocation + '\'' + '}';
}


public String getExpectLocation(){
    return expectLocation;
}


public void setExpectLocation(String expectLocation){
    this.expectLocation = expectLocation;
}


}