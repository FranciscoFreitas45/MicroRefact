package com.puffride.demo.DTO;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import javax.print.attribute.standard.Destination;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.puffride.demo.Request.LocationRequest;
import com.puffride.demo.Request.Impl.LocationRequestImpl;
import com.puffride.demo.DTO.Location;
import com.puffride.demo.Request.UserRequest;
import com.puffride.demo.Request.Impl.UserRequestImpl;
import com.puffride.demo.DTO.User;
import com.puffride.demo.Request.LocationRequest;
import com.puffride.demo.Request.Impl.LocationRequestImpl;
import com.puffride.demo.DTO.Location;
public class Schedule implements Serializable{

 private  long serialVersionUID;

 private  Integer schedule;

 private  Location origin;

 private  User creator;

 private  Location destination;

 private  LocalTime timeOfDay;

 private  Integer dow;

 private  LocalDate startDate;

 private  LocalDate endDate;

 private  LocalDate createDate;

 private  LocalDateTime updateDate;

 private Integer location;

 private Integer user;

 private Integer location;


}