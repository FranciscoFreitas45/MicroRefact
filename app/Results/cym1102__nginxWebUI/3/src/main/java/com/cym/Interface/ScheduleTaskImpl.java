package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.ScheduleTask;
public class ScheduleTaskImpl implements ScheduleTask{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


}