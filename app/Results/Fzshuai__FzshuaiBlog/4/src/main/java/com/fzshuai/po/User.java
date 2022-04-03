package com.fzshuai.po;
 import javax.persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fzshuai.Request.BlogRequest;
import com.fzshuai.Request.Impl.BlogRequestImpl;
import com.fzshuai.DTO.Blog;
@Entity
@Table(name = "t_user")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

 private  String nickname;

 private  String username;

 private  String password;

 private  String email;

 private  String avatar;

 private  Integer type;

@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;

@Transient
 private  List<Blog> blogs;

@Transient
 private BlogRequest blogrequest = new BlogRequestImpl();;

public User() {
}
public void setPassword(String password){
    this.password = password;
}


public Date getCreateTime(){
    return createTime;
}


public void setUsername(String username){
    this.username = username;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public String getAvatar(){
    return avatar;
}


public Long getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setType(Integer type){
    this.type = type;
}


public void setBlogs(List<Blog> blogs){
 blogrequest.setBlogs(blogs,this.id);
}



public List<Blog> getBlogs(){
  this.blogs = blogrequest.getBlogs(this.id);
return this.blogs;
}


public String getUsername(){
    return username;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getPassword(){
    return password;
}


public String getNickname(){
    return nickname;
}


public void setEmail(String email){
    this.email = email;
}


public Integer getType(){
    return type;
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


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "User{" + "id=" + id + ", nickname='" + nickname + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", avatar='" + avatar + '\'' + ", type=" + type + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
}


}