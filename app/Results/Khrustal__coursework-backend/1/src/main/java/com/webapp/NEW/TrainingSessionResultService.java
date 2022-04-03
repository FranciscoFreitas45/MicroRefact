package com.webapp.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.repository.TrainingSessionRepository;
import com.webapp.models.TrainingSession;
@Service
public class TrainingSessionResultService {

@Autowired
 private TrainingSessionRepository trainingsessionrepository;


public void setSession(Long idOXI5,TrainingSession session){
trainingsessionrepository.setSession(idOXI5,session);
}


}