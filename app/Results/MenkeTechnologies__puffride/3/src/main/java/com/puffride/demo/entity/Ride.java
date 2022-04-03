package com.puffride.demo.entity;
 import lombok;
import java.io.Serializable;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.puffride.demo.Request.CarRequest;
import com.puffride.demo.Request.Impl.CarRequestImpl;
import com.puffride.demo.DTO.Car;
import com.puffride.demo.Request.ScheduleRequest;
import com.puffride.demo.Request.Impl.ScheduleRequestImpl;
import com.puffride.demo.DTO.Schedule;
import com.puffride.demo.Request.DriverRequest;
import com.puffride.demo.Request.Impl.DriverRequestImpl;
import com.puffride.demo.DTO.Driver;
import com.puffride.demo.Request.RiderRequest;
import com.puffride.demo.Request.Impl.RiderRequestImpl;
import com.puffride.demo.DTO.Rider;
@Entity
@Table(name = "RIDE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ride implements Serializable{

 private  long serialVersionUID;

@Id
@Column(name = "RIDE_ID")
 private  Integer ride;

@Transient
 private  Car car;

@Transient
 private  Schedule schedule;

@Transient
 private  Driver driver;

@Transient
 private  Rider rider;

@Column(name = "START_TIME")
 private  LocalDateTime startTime;

@Column(name = "END_TIME")
 private  LocalDateTime endTime;

@Column(name = "CREATE_DATE")
 private  LocalDate createDate;

@Column(name = "UPDATE_DATE")
 private  LocalDateTime updateDate;

@Column(name = "carv2")
 private Integer carv2;

@Transient
 private CarRequest carrequest = new CarRequestImpl();;

@Column(name = "schedulev2")
 private Integer schedulev2;

@Transient
 private ScheduleRequest schedulerequest = new ScheduleRequestImpl();;

@Column(name = "driverv2")
 private Integer driverv2;

@Transient
 private DriverRequest driverrequest = new DriverRequestImpl();;

@Column(name = "riderv2")
 private Integer riderv2;

@Transient
 private RiderRequest riderrequest = new RiderRequestImpl();;


}