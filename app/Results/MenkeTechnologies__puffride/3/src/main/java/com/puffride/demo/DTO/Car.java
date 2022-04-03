package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Car implements Serializable{

 private  long serialVersionUID;

 private  Integer car;

 private  Integer mpg;

 private  Integer plateNumber;

 private  Integer year;

 private  Integer seats;

 private  String specialNeedsFlag;

 private  String carPicture;

 private  String color;

 private  String make;

 private  String model;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;


}