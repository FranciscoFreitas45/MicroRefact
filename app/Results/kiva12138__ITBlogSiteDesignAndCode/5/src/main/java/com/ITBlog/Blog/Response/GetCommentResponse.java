package com.ITBlog.Blog.Response;
 import java.sql.Date;
public class GetCommentResponse {

 private  long commentId;

 private  long blogId;

 private  Date time;

 private  long authorId;

 private  String content;

 private  int code;

 private  String needInformation;


public String getNeedInformation(){
    return needInformation;
}


public void setContent(String content){
    this.content = content;
}


public void setNeedInformation(String needInformation){
    this.needInformation = needInformation;
}


public long getBlogId(){
    return blogId;
}


public Date getTime(){
    return time;
}


public String getContent(){
    return content;
}


public void setCode(int code){
    this.code = code;
}


public void setBlogId(long blogId){
    this.blogId = blogId;
}


public long getCommentId(){
    return commentId;
}


public void setAuthorId(long authorId){
    this.authorId = authorId;
}


public long getAuthorId(){
    return authorId;
}


public void setCommentId(long commentId){
    this.commentId = commentId;
}


public int getCode(){
    return code;
}


public void setTime(Date time){
    this.time = time;
}


}