package com.puffride.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.repository.CarRepository;
import com.puffride.demo.entity.Car;
@Service
public class CarRideService {

@Autowired
 private CarRepository carrepository;


}