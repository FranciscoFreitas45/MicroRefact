package org.live.app.vo;
 public class ApplyAnchorVo {

 private  String categoryId;

 private  String userId;

 private  String idCard;

 private  String realName;

 private  String idImgUrl;


public void setRealName(String realName){
    this.realName = realName;
}


public String getIdCard(){
    return idCard;
}


public void setIdImgUrl(String idImgUrl){
    this.idImgUrl = idImgUrl;
}


public String getCategoryId(){
    return categoryId;
}


public void setCategoryId(String categoryId){
    this.categoryId = categoryId;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public String getIdImgUrl(){
    return idImgUrl;
}


public String getRealName(){
    return realName;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}