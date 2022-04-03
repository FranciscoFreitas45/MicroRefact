package com.fzshuai.DTO;
 import javax.persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fzshuai.Request.BlogRequest;
import com.fzshuai.Request.Impl.BlogRequestImpl;
import com.fzshuai.DTO.Blog;
public class User {

 private  Long id;

 private  String nickname;

 private  String username;

 private  String password;

 private  String email;

 private  String avatar;

 private  Integer type;

 private  Date createTime;

 private  Date updateTime;

 private  List<Blog> blogs;

public User() {
}
public Date getCreateTime(){
    return createTime;
}


public String getAvatar(){
    return avatar;
}


public Long getId(){
    return id;
}


public List<Blog> getBlogs(){
    return blogs;
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


public Integer getType(){
    return type;
}


public String getEmail(){
    return email;
}


}