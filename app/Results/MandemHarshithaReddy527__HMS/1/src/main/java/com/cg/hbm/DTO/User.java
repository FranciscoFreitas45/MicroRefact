package com.cg.hbm.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class User {

 private  int user_id;

 private  String user_name;

 private  String email;

 private  String password;

 private  String role;

 private  String mobile;

 private  String address;

public User() {
    super();
}/**
 * @param user_name
 * @param email
 * @param password
 * @param role
 * @param mobile
 * @param address
 */
public User(String user_name, String email, String password, String role, String mobile, String address) {
    super();
    // this.user_id=user_id;
    this.user_name = user_name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.mobile = mobile;
    this.address = address;
}
public String getRole(){
    return role;
}


public String getUser_name(){
    return user_name;
}


public int getUser_id(){
    return user_id;
}


public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public String getMobile(){
    return mobile;
}


public String getAddress(){
    return address;
}


}