package com.sprint2.controller;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Scheduler;
public interface ISchedulerController {


@ResponseBody
public Scheduler getSchedulerById(Integer schedulerId)
;

@ResponseBody
public Scheduler updateScheduler(Scheduler scheduler)
;

@ResponseBody
public List<Scheduler> getAllScheduler()
;

@ResponseBody
public Scheduler addScheduler(Scheduler scheduler)
;

@ResponseBody
public boolean deleteScheduler(Integer schedulerId)
;

}