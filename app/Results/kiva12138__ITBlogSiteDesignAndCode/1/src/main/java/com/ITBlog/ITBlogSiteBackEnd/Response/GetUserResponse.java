package com.ITBlog.ITBlogSiteBackEnd.Response;
 public class GetUserResponse {

 private  int code;

 private  String description;

 private  long userId;

 private  String name;

 private  int gender;

 private  int age;

 private  int blogNum;

 private  long phone;


public void setName(String name){
    this.name = name;
}


public long getPhone(){
    return phone;
}


public int getAge(){
    return age;
}


public String getName(){
    return name;
}


public void setCode(int code){
    this.code = code;
}


public int getBlogNum(){
    return blogNum;
}


public int getGender(){
    return gender;
}


public void setPhone(long phone){
    this.phone = phone;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setGender(int gender){
    this.gender = gender;
}


public void setBlogNum(int blogNum){
    this.blogNum = blogNum;
}


public int getCode(){
    return code;
}


public long getUserId(){
    return userId;
}


public void setUserId(long userId){
    this.userId = userId;
}


public void setAge(int age){
    this.age = age;
}


}