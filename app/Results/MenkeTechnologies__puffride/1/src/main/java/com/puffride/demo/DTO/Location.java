package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Location implements Serializable{

 private  long serialVersionUID;

 private  Integer location;

 private  String driverVerifiedFlag;

 private  String icon;

 private  double latitude;

 private  double longitude;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;


}