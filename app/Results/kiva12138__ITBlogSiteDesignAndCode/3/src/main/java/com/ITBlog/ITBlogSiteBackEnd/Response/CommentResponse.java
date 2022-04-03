package com.ITBlog.ITBlogSiteBackEnd.Response;
 public class CommentResponse {

 private  int code;

 private  String needInformation;


public String getNeedInformation(){
    return needInformation;
}


public void setNeedInformation(String needInformation){
    this.needInformation = needInformation;
}


public void setCode(int code){
    this.code = code;
}


public int getCode(){
    return code;
}


}