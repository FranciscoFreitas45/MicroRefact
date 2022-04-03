package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Rider implements Serializable{

 private  long serialVersionUID;

 private  Integer rider;

 private  String driverVerifiedFlag;

 private  User user;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;


}