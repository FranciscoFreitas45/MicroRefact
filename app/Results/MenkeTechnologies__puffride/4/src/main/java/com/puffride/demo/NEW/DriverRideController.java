package com.puffride.demo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.entity.Driver;
@RestController
@CrossOrigin
public class DriverRideController {

@Autowired
 private DriverRideService driverrideservice;


}