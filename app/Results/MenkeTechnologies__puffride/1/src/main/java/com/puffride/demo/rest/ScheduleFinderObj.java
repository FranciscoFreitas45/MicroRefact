package com.puffride.demo.rest;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleFinderObj {

 private  LocationWrapper origin;

 private  LocationWrapper destination;

 private  Integer dow;

 private  LocalTime timeOfDay;


}