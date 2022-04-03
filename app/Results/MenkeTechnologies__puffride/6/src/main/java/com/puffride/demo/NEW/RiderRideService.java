package com.puffride.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.repository.RiderRepository;
import com.puffride.demo.entity.Rider;
@Service
public class RiderRideService {

@Autowired
 private RiderRepository riderrepository;


}