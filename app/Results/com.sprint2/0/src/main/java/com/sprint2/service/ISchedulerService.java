package com.sprint2.service;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sprint2.model.Scheduler;
public interface ISchedulerService {


public Scheduler getSchedulerById(Integer schedulerId)
;

public boolean deleteSchedulerbyId(Integer schedulerId)
;

public List<Scheduler> getAllSchedulers()
;

public Scheduler updateScheduler(Scheduler scheduler)
;

public Scheduler addScheduler(Scheduler scheduler)
;

}