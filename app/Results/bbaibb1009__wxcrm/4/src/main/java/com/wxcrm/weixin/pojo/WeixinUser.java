package com.wxcrm.weixin.pojo;
 import net.sf.json.JSONObject;
public class WeixinUser {

 private  String sex;

 private  String subscribe;

 private  String remark;

 private  String nickname;

 private  String province;

 private  String openid;

 private  String language;

 private  String headimgurl;

 private  String subscribe_time;

 private  String country;

 private  String city;

public WeixinUser(JSONObject obj) {
    // 1,
    this.sex = obj.getString("sex");
    // 1,
    this.subscribe = obj.getString("subscribe");
    // "",
    this.remark = obj.getString("remark");
    // "���ɿ���",
    this.nickname = obj.getString("nickname");
    // "ɽ��",
    this.province = obj.getString("province");
    // "oFVvzsk82VGxsVDrT8YhWzwaTDXE",
    this.openid = obj.getString("openid");
    // "zh_CN",
    this.language = obj.getString("language");
    // "http://wx.qlogo.cn/mmopen/PiajxSqBRaEJKcAuNafuwWicoYCLjhQ6pRGnwWDRrppaHMLiaxibIicgnuozDVd0u3Iia7ra6ufEygiawZavwUvNHu06w/0",
    this.headimgurl = obj.getString("headimgurl");
    // 1429077215,
    this.subscribe_time = obj.getString("subscribe_time");
    // "�й�",
    this.country = obj.getString("country");
    // "�Ͳ�"\
    this.city = obj.getString("city");
}
public String getOpenid(){
    return openid;
}


public void setCountry(String country){
    this.country = country;
}


public String getLanguage(){
    return language;
}


public void setProvince(String province){
    this.province = province;
}


public String getCountry(){
    return country;
}


public void setCity(String city){
    this.city = city;
}


public void setSex(String sex){
    this.sex = sex;
}


public String getSubscribe(){
    return subscribe;
}


public String getNickname(){
    return nickname;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setOpenid(String openid){
    this.openid = openid;
}


public String getHeadimgurl(){
    return headimgurl;
}


public void setHeadimgurl(String headimgurl){
    this.headimgurl = headimgurl;
}


public String getSex(){
    return sex;
}


public String getProvince(){
    return province;
}


public void setSubscribe(String subscribe){
    this.subscribe = subscribe;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getSubscribe_time(){
    return subscribe_time;
}


public void setSubscribe_time(String subscribeTime){
    subscribe_time = subscribeTime;
}


public void setLanguage(String language){
    this.language = language;
}


public String getCity(){
    return city;
}


}