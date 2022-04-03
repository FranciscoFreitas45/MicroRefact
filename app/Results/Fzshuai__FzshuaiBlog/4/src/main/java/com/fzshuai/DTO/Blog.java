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

 private Long id;

 private Long id;

public Blog() {
}
public Date getCreateTime(){
    return createTime;
}


public String getContent(){
    return content;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public Long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public String getTitle(){
    return title;
}


public List<Comment> getComments(){
  this.comments = commentrequest.getComments(this.id);
return this.comments;
}


public String getFlag(){
    return flag;
}


public String getFirstPicture(){
    return firstPicture;
}


public Date getUpdateTime(){
    return updateTime;
}


public Integer getViews(){
    return views;
}


public Type getType(){
  this.type = typerequest.getType(this.id);
return this.type;
}


}