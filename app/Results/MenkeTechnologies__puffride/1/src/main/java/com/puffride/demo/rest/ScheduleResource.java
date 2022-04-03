package com.puffride.demo.rest;
 import com.puffride.demo.dao.RideDao;
import com.puffride.demo.dao.RiderDao;
import com.puffride.demo.entity.Ride;
import com.puffride.demo.entity.User;
import org.springframework.web.bind.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.puffride.demo.entity.Schedule;
import com.puffride.demo.dao.ScheduleDao;
import com.puffride.demo.utils.GlobalConstants;
import com.puffride.demo.Interface.RideDao;
@RestController
@RequestMapping(GlobalConstants.CONTEXT_PATH + "/schedule")
public class ScheduleResource {

@Autowired
 private ScheduleDao dao;

@Autowired
 private RideDao riderDao;


@PostMapping("findSchedulesWithNoRidesByEmail")
public List<Schedule> findSchedulesWithNoRidesByEmail(EmailObj emailObj){
    List<Schedule> schedules = dao.findAll().stream().filter(sched -> sched.getCreator().getEmail().equalsIgnoreCase(emailObj.getEmail())).collect(Collectors.toList());
    List<Schedule> unmatchedSchedules = new ArrayList<>();
    for (Schedule schedule : schedules) {
        List<Ride> rides = riderDao.findAll().stream().filter(r -> r.getSchedule().equals(schedule)).collect(Collectors.toList());
        if (rides.isEmpty()) {
            unmatchedSchedules.add(schedule);
        }
    }
    return unmatchedSchedules;
}


@GetMapping("/{id}")
public Schedule read(Long id){
    return dao.findOne(id);
}


@PostMapping("findSchedulesWithRidesByEmail")
public List<Schedule> findSchedulesWithRidesByEmail(EmailObj emailObj){
    List<Schedule> schedules = dao.findAll().stream().filter(sched -> sched.getCreator().getEmail().equalsIgnoreCase(emailObj.getEmail())).collect(Collectors.toList());
    List<Schedule> matchedSchedules = new ArrayList<>();
    for (Schedule schedule : schedules) {
        List<Ride> rides = riderDao.findAll().stream().filter(r -> r.getSchedule().equals(schedule)).collect(Collectors.toList());
        if (!rides.isEmpty()) {
            matchedSchedules.add(schedule);
        }
    }
    return matchedSchedules;
}


@DeleteMapping
public boolean deleteAll(List<Schedule> entityList){
    dao.deleteAll(entityList);
    return true;
}


@PostMapping
public Schedule create(Schedule entity){
    return dao.save(entity);
}


@PutMapping
public Schedule update(Schedule entity){
    return dao.save(entity);
}


@PostMapping("findRidesByEmail")
public List<Ride> findRidesByEmail(EmailObj emailObj){
    List<Ride> rides = riderDao.findAll().stream().filter(r -> r.getRider().getUser().getEmail().equalsIgnoreCase(emailObj.getEmail())).collect(Collectors.toList());
    return rides;
}


@PostMapping("findSchedulesByEmail")
public List<Schedule> findSchedulesByEmail(EmailObj emailObj){
    List<Ride> rides = riderDao.findAll().stream().filter(r -> r.getRider().getUser().getEmail().equalsIgnoreCase(emailObj.getEmail())).collect(Collectors.toList());
    List<Schedule> schedules = rides.stream().map(Ride::getSchedule).collect(Collectors.toList());
    return schedules;
}


@DeleteMapping("/{id}")
public boolean delete(Long id){
    dao.delete(id);
    return true;
}


@GetMapping
public List<Schedule> readAll(){
    return dao.findAll();
}


@PostMapping("findMatchingSchedules")
public List<Schedule> findMatchingSchedules(ScheduleFinderObj scheduleFinderObj){
    List<Schedule> schedules = dao.findAll();
    List<Schedule> matched = new ArrayList<>();
    for (Schedule fr : schedules) {
        if (fr.getOrigin().getLatitude() == scheduleFinderObj.getOrigin().getLatitude() && fr.getOrigin().getLongitude() == scheduleFinderObj.getOrigin().getLongitude()) {
            System.out.println("\n_____________\"origin\" = " + "origin" + "_____________\n");
            if (fr.getDestination().getLongitude() == scheduleFinderObj.getDestination().getLongitude() && fr.getDestination().getLatitude() == scheduleFinderObj.getDestination().getLatitude()) {
                System.out.println("\n_____________\"desting\" = " + "desting" + "_____________\n");
                if (fr.getDow().equals(scheduleFinderObj.getDow())) {
                    if (scheduleFinderObj.getTimeOfDay().equals(fr.getTimeOfDay())) {
                        matched.add(fr);
                    }
                }
            }
        }
    }
    return matched;
}


}