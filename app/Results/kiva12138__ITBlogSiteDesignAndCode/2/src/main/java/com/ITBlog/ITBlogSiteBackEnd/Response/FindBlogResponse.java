package com.ITBlog.ITBlogSiteBackEnd.Response;
 import java.util.List;
import com.ITBlog.ITBlogSiteBackEnd.Entity.Blog;
public class FindBlogResponse {

 private  int code;

 private  String otherInformation;

 private  List<Blog> findBlog;


public void setFindBlog(List<Blog> findBlog){
    this.findBlog = findBlog;
}


public void setCode(int code){
    this.code = code;
}


public List<Blog> getFindBlog(){
    return findBlog;
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