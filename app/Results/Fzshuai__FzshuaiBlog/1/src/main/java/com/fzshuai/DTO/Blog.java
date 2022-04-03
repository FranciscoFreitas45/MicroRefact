package com.fzshuai.DTO;
 import javax.persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fzshuai.Request.TypeRequest;
import com.fzshuai.Request.Impl.TypeRequestImpl;
import com.fzshuai.DTO.Type;
import com.fzshuai.Request.UserRequest;
import com.fzshuai.Request.Impl.UserRequestImpl;
import com.fzshuai.DTO.User;
import com.fzshuai.Request.CommentRequest;
import com.fzshuai.Request.Impl.CommentRequestImpl;
import com.fzshuai.DTO.Comment;
public class Blog {

 private  Long id;

 private  String title;

 private  String content;

 private  String firstPicture;

 private  String flag;

 private  Integer views;

 private  boolean appreciation;

 private  boolean shareStatement;

 private  boolean commentated;

 private  boolean published;

 private  boolean recommend;

 private  Date createTime;

 private  Date updateTime;

 private  Type type;

 private  User user;

 private  List<Comment> comments;

 private  String description;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

public Blog() {
}
public void setContent(String content){
    this.content = content;
}


public Date getCreateTime(){
    return createTime;
}


public String getContent(){
    return content;
}


public User getUser(){
    return user;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public void setRecommend(boolean recommend){
    this.recommend = recommend;
}


public String getDescription(){
    return description;
}


public String getTitle(){
    return title;
}


public List<Comment> getComments(){
    return comments;
}


public void setUser(User user){
    this.user = user;
}


public String getFlag(){
    return flag;
}


public void setFlag(String flag){
    this.flag = flag;
}


public boolean isPublished(){
    return published;
}


public void setPublished(boolean published){
    this.published = published;
}


public void setType(Type type){
    this.type = type;
}


public String getFirstPicture(){
    return firstPicture;
}


public Date getUpdateTime(){
    return updateTime;
}


public boolean isAppreciation(){
    return appreciation;
}


public Integer getViews(){
    return views;
}


public Type getType(){
    return type;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public boolean isCommentated(){
    return commentated;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreateTime"))

.queryParam("createTime",createTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setViews(Integer views){
    this.views = views;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setViews"))

.queryParam("views",views)
;
restTemplate.put(builder.toUriString(),null);
}


}