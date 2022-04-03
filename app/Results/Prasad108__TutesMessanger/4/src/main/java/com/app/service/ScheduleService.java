package com.app.service;
 import java.util.List;
import com.app.pojo.Division;
import com.app.pojo.Schedule;
public interface ScheduleService {


public List<Schedule> getall()
;

public Schedule edit(int id)
;

public Schedule find(int id)
;

public void create(Schedule schedule)
;

public void update(Schedule schedule)
;

public void delet(int id)
;

public Schedule fordivision(int divID)
;

public int updateScheduleOfinstitute(Schedule schedule)
;

}