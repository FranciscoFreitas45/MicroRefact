package com.fzshuai.po;
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
@Entity
@Table(name = "t_blog")
public class Blog {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

 private  String title;

@Basic(fetch = FetchType.LAZY)
@Lob
 private  String content;

 private  String firstPicture;

 private  String flag;

 private  Integer views;

 private  boolean appreciation;

 private  boolean shareStatement;

 private  boolean commentated;

 private  boolean published;

 private  boolean recommend;

@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Transient
 private  Type type;

@Transient
 private  User user;

@Transient
 private  List<Comment> comments;

 private  String description;

@Column(name = "id")
 private Long id;

@Transient
 private TypeRequest typerequest = new TypeRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Transient
 private CommentRequest commentrequest = new CommentRequestImpl();;

public Blog() {
}
public boolean isRecommend(){
    return recommend;
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
  this.user = userrequest.getUser(this.id);
return this.user;
}


public Long getId(){
    return id;
}


public boolean isShareStatement(){
    return shareStatement;
}


public void setDescription(String description){
    this.description = description;
}


public void setCommentated(boolean commentated){
    this.commentated = commentated;
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
  this.comments = commentrequest.getComments(this.id);
return this.comments;
}


public void setId(Long id){
    this.id = id;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



public void setComments(List<Comment> comments){
 commentrequest.setComments(comments,this.id);
}



public String getFlag(){
    return flag;
}


public void setFirstPicture(String firstPicture){
    this.firstPicture = firstPicture;
}


public void setFlag(String flag){
    this.flag = flag;
}


public void setShareStatement(boolean shareStatement){
    this.shareStatement = shareStatement;
}


public boolean isPublished(){
    return published;
}


public void setTitle(String title){
    this.title = title;
}


public void setPublished(boolean published){
    this.published = published;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setType(Type type){
 typerequest.setType(type,this.id);
}



public String getFirstPicture(){
    return firstPicture;
}


public Date getUpdateTime(){
    return updateTime;
}


public void setAppreciation(boolean appreciation){
    this.appreciation = appreciation;
}


public boolean isAppreciation(){
    return appreciation;
}


public Integer getViews(){
    return views;
}


public Type getType(){
  this.type = typerequest.getType(this.id);
return this.type;
}


public void setViews(Integer views){
    this.views = views;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "Blog{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\'' + ", firstPicture='" + firstPicture + '\'' + ", flag='" + flag + '\'' + ", views=" + views + ", appreciation=" + appreciation + ", shareStatement=" + shareStatement + ", commentated=" + commentated + ", published=" + published + ", recommend=" + recommend + ", createTime=" + createTime + ", updateTime=" + updateTime + ", type=" + type + ", user=" + user + ", comments=" + comments + ", description='" + description + '\'' + '}';
}


public boolean isCommentated(){
    return commentated;
}


}