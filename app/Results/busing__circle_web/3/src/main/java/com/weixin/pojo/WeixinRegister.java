package com.weixin.pojo;
 public class WeixinRegister {

 private  int id;

 private  String weixin_id;

 private  String mobile_phone;

 private  int step;


public String getWeixin_id(){
    return weixin_id;
}


public void setWeixin_id(String weixin_id){
    this.weixin_id = weixin_id;
}


public void setMobile_phone(String mobile_phone){
    this.mobile_phone = mobile_phone;
}


public int getStep(){
    return step;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


public String getMobile_phone(){
    return mobile_phone;
}


public void setStep(int step){
    this.step = step;
}


}