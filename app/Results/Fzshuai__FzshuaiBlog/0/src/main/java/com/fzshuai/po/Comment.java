package com.fzshuai.po;
 import javax.persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fzshuai.Request.BlogRequest;
import com.fzshuai.Request.Impl.BlogRequestImpl;
import com.fzshuai.DTO.Blog;
@Entity
@Table(name = "t_comment")
public class Comment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

 private  String nickname;

 private  String email;

 private  String content;

 private  String avatar;

@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Transient
 private  Blog blog;

@OneToMany(mappedBy = "parentComment")
 private  List<Comment> replyComments;

@ManyToOne
 private  Comment parentComment;

 private  boolean adminComment;

@Column(name = "id")
 private Long id;

@Transient
 private BlogRequest blogrequest = new BlogRequestImpl();;

public Comment() {
}
public boolean isAdminComment(){
    return adminComment;
}


public void setContent(String content){
    this.content = content;
}


public Date getCreateTime(){
    return createTime;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public Blog getBlog(){
  this.blog = blogrequest.getBlog(this.id);
return this.blog;
}


public void setReplyComments(List<Comment> replyComments){
    this.replyComments = replyComments;
}


public void setAdminComment(boolean adminComment){
    this.adminComment = adminComment;
}


public String getAvatar(){
    return avatar;
}


public String getContent(){
    return content;
}


public Long getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public List<Comment> getReplyComments(){
    return replyComments;
}


public void setParentComment(Comment parentComment){
    this.parentComment = parentComment;
}


public void setBlog(Blog blog){
 blogrequest.setBlog(blog,this.id);
}



public String getNickname(){
    return nickname;
}


public void setEmail(String email){
    this.email = email;
}


public void setId(Long id){
    this.id = id;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "Comment{" + "id=" + id + ", nickname='" + nickname + '\'' + ", email='" + email + '\'' + ", content='" + content + '\'' + ", avatar='" + avatar + '\'' + ", createTime=" + createTime + ", blog=" + blog + ", replyComments=" + replyComments + ", parentComment=" + parentComment + ", adminComment=" + adminComment + '}';
}


public Comment getParentComment(){
    return parentComment;
}


}