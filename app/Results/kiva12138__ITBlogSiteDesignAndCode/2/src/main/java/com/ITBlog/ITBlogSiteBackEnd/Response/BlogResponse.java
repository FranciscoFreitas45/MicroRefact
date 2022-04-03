package com.ITBlog.ITBlogSiteBackEnd.Response;
 public class BlogResponse {

 private  int code;

 private  String otherInformation;


public void setCode(int code){
    this.code = code;
}


public String getOtherInformation(){
    return otherInformation;
}


public void setOtherInformation(String otherInformation){
    this.otherInformation = otherInformation;
}


public int getCode(){
    return code;
}


}