package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Driver implements Serializable{

 private  long serialVersionUID;

 private  Integer driver;

 private  String driverVerifiedFlag;

 private  User user;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;


}