package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.calendars.dao.SchedulEventDao;
import com.hmm.calendars.entity.SchedulEvent;
@Service
public class SchedulEventEmployeeService {

@Autowired
 private SchedulEventDao scheduleventdao;


public List<SchedulEvent> getSchedulEventlist(Integer emp_id){
return scheduleventdao.getSchedulEventlist(emp_id);
}


public void setSchedulEventlist(Integer emp_id,List<SchedulEvent> schedulEventlist){
scheduleventdao.setSchedulEventlist(emp_id,schedulEventlist);
}


}