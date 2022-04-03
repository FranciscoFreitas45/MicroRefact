package com.puffride.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.repository.RideRepository;
import com.puffride.demo.entity.Ride;
@Service
public class RideRatingService {

@Autowired
 private RideRepository riderepository;


}