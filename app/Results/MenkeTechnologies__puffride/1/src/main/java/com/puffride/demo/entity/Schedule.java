package com.puffride.demo.entity;
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
@Entity
@Table(name = "SCHEDULE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "SCHEDULE_ID")
 private  Integer schedule;

@Transient
 private  Location origin;

@Transient
 private  User creator;

@Transient
 private  Location destination;

@Column(name = "TIME_OF_DAY")
 private  LocalTime timeOfDay;

@Column(name = "DOW")
 private  Integer dow;

@Column(name = "START_DATE")
 private  LocalDate startDate;

@Column(name = "END_DATE")
 private  LocalDate endDate;

@Column(name = "CREATE_DATE")
 private  LocalDate createDate;

@Column(name = "UPDATE_DATE")
 private  LocalDateTime updateDate;

@Column(name = "location")
 private Integer location;

@Transient
 private LocationRequest locationrequest = new LocationRequestImpl();;

@Column(name = "user")
 private Integer user;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "location")
 private Integer location;

@Transient
 private LocationRequest locationrequest = new LocationRequestImpl();;


}