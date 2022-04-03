package com.puffride.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.repository.ScheduleRepository;
import com.puffride.demo.entity.Schedule;
@Service
public class ScheduleRideService {

@Autowired
 private ScheduleRepository schedulerepository;


}