package com.webapp.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.DTO.TrainingSession;
import com.webapp.Request.TrainingSessionRequest;
public class TrainingSessionRequestImpl implements TrainingSessionRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setSession(TrainingSession session,Long idOXI5){
 restTemplate.put("http://1/Result/{id}/TrainingSession/setSession",session,idOXI5);
 return ;
}


}