package com.puffride.demo.DTO;
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