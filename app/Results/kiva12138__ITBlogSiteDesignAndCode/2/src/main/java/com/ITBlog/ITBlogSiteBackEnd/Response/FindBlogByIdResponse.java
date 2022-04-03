package com.ITBlog.ITBlogSiteBackEnd.Response;
 import com.ITBlog.ITBlogSiteBackEnd.Entity.Blog;
public class FindBlogByIdResponse {

 private  int code;

 private  String otherInformation;

 private  Blog findBlog;


public void setFindBlogById(Blog findBlog){
    this.findBlog = findBlog;
}


public Blog getFindBlogById(){
    return findBlog;
}


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