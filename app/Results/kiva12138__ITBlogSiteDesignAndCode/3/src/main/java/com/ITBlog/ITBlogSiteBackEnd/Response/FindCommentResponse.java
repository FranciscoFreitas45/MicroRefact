package com.ITBlog.ITBlogSiteBackEnd.Response;
 import java.util.List;
import com.ITBlog.ITBlogSiteBackEnd.Entity.Comment;
public class FindCommentResponse {

 private  int code;

 private  String otherInformation;

 private  List<Comment> comments;


public void setCode(int code){
    this.code = code;
}


public String getOtherInformation(){
    return otherInformation;
}


public List<Comment> getComments(){
    return comments;
}


public void setOtherInformation(String otherInformation){
    this.otherInformation = otherInformation;
}


public void setComments(List<Comment> comments){
    this.comments = comments;
}


public int getCode(){
    return code;
}


}