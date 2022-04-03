package com.puffride.demo.entity;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "LOCATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "LOCATION_ID")
 private  Integer location;

@Column(name = "DRIVER_VERIFIED_FLAG")
 private  String driverVerifiedFlag;

@Column(name = "ICON")
 private  String icon;

@Column(name = "LATITUDE")
 private  double latitude;

@Column(name = "LONGITUDE")
 private  double longitude;

@Column(name = "CREATE_DATE")
 private  LocalDate createDate;

@Column(name = "UPDATE_DATE")
 private  LocalDateTime updateDate;


}