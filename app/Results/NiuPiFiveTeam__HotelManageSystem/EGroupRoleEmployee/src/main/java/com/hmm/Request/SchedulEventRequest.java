package com.hmm.Request;
import com.hmm.DTO.SchedulEvent;
public interface SchedulEventRequest {

   public List<SchedulEvent> getSchedulEventlist(Integer emp_id);
   public void setSchedulEventlist(List<SchedulEvent> schedulEventlist,Integer emp_id);
}