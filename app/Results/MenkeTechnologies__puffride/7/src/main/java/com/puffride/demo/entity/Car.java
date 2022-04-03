package com.puffride.demo.entity;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "CAR")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "CAR_ID")
 private  Integer car;

@Column(name = "MPG")
 private  Integer mpg;

@Column(name = "PLATE_NUMBER")
 private  Integer plateNumber;

@Column(name = "YEAR")
 private  Integer year;

@Column(name = "SEATS")
 private  Integer seats;

@Column(name = "SPECIAL_NEEDS_FLAG")
 private  String specialNeedsFlag;

@Column(name = "CAR_PICTURE")
 private  String carPicture;

@Column(name = "COLOR")
 private  String color;

@Column(name = "MAKE")
 private  String make;

@Column(name = "MODEL")
 private  String model;

@Column(name = "CREATE_DATE")
 private  LocalDate createDate;

@Column(name = "UPDATE_DATE")
 private  LocalDateTime updateDate;


}