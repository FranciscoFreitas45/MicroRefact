package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.SchedulEvent;
import com.hmm.Request.SchedulEventRequest;
public class SchedulEventRequestImpl implements SchedulEventRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<SchedulEvent> getSchedulEventlist(Integer emp_id){
 List<SchedulEvent> aux = restTemplate.getForObject("http://8/Employee/{id}/SchedulEvent/getSchedulEventlist",List<SchedulEvent>.class,emp_id);
return aux;
}


public void setSchedulEventlist(List<SchedulEvent> schedulEventlist,Integer emp_id){
 restTemplate.put("http://8/Employee/{id}/SchedulEvent/setSchedulEventlist",schedulEventlist,emp_id);
 return ;
}


}