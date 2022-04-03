package com.ITBlog.ITBlogSiteBackEnd.Entity;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "User")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long userId;

 private  String name;

 private  int gender;

 private  int age;

 private  String password;

 private  int blogNum;

 private  int type;

 private  long phone;

/**
 * 构造函数
 * @param name
 * @param gender
 * @param age
 * @param password
 * @param blogNum
 * @param type
 * @param phone
 */
public User(String name, int gender, int age, String password, int blogNum, int type, long phone) {
    this.name = name;
    this.gender = gender;
    this.age = age;
    this.password = password;
    this.blogNum = blogNum;
    this.type = type;
    this.phone = phone;
}public User() {
    this.name = "";
    this.gender = 1;
    this.age = 0;
    this.password = "";
    this.blogNum = 0;
    this.type = 1;
    this.phone = 0;
}
public void setName(String name){
    this.name = name;
}


public long getPhone(){
    return phone;
}


public int getAge(){
    return age;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
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


public void setType(int type){
    this.type = type;
}


public void setGender(int gender){
    this.gender = gender;
}


public String getPassword(){
    return password;
}


public int getType(){
    return type;
}


public void setBlogNum(int blogNum){
    this.blogNum = blogNum;
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