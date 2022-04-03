package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Ride implements Serializable{

 private  long serialVersionUID;

 private  Integer ride;

 private  Car car;

 private  Schedule schedule;

 private  Driver driver;

 private  Rider rider;

 private  LocalDateTime startTime;

 private  LocalDateTime endTime;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;


}