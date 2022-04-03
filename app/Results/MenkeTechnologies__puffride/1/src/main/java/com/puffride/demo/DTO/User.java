package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class User implements Serializable{

 private  long serialVersionUID;

 private  Integer user;

 private  String name;

 private  String profilePicture;

 private  String pwHash;

 private  String email;

 private  String bio;

 private  String emailVerifiedFlag;

 private  Phone phone;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;


}