package com.puffride.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.repository.DriverRepository;
import com.puffride.demo.entity.Driver;
@Service
public class DriverRideService {

@Autowired
 private DriverRepository driverrepository;


}