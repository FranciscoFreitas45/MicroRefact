package edu.nju.careerbridge.youth.bean;
 public class HonorBean {

 private  String phone;

 private  String honorName;

 private  String level;

public HonorBean() {
}public HonorBean(String phone, String honorName, String level) {
    this.phone = phone;
    this.honorName = honorName;
    this.level = level;
}
public String getPhone(){
    return phone;
}


public String getHonorName(){
    return honorName;
}


public void setHonorName(String honorName){
    this.honorName = honorName;
}


public String getLevel(){
    return level;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Override
public String toString(){
    return "HonorBean{" + "phone='" + phone + '\'' + ", honorName='" + honorName + '\'' + ", level='" + level + '\'' + '}';
}


public void setLevel(String level){
    this.level = level;
}


}