package com.sprint2.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Scheduler;
import com.sprint2.service.SchedulerService;
@Controller
@RequestMapping("/Scheduler")
public class SchedulerController implements ISchedulerController{

 private Logger logger;

@Autowired
 private  SchedulerService schedulerService;


@GetMapping("/{schedulerId}")
@ResponseBody
public Scheduler getSchedulerById(Integer schedulerId){
    logger.info("Scheduler service was instalized");
    return schedulerService.getSchedulerById(schedulerId);
}


@PutMapping("/")
@ResponseBody
public Scheduler updateScheduler(Scheduler scheduler){
    return schedulerService.updateScheduler(scheduler);
}


@GetMapping("/")
@ResponseBody
public List<Scheduler> getAllScheduler(){
    List<Scheduler> schedulerlist = schedulerService.getAllSchedulers();
    return schedulerlist;
}


@PostMapping("/")
@ResponseBody
public Scheduler addScheduler(Scheduler scheduler){
    return schedulerService.addScheduler(scheduler);
}


@DeleteMapping("/{schedulerId}")
@ResponseBody
public boolean deleteScheduler(Integer schedulerId){
    return schedulerService.deleteSchedulerbyId(schedulerId);
}


}