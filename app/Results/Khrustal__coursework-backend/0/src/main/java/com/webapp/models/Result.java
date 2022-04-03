package com.webapp.models;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.webapp.Request.TrainingSessionRequest;
import com.webapp.Request.Impl.TrainingSessionRequestImpl;
import com.webapp.DTO.TrainingSession;
@Entity
public class Result {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Transient
 private  TrainingSession session;

@Column(name = "session_id")
 private  Long sessionId;

@OneToMany
 private  List<Answer> answer;

@NotBlank
 private  double rightAnswers;

@NotBlank
 private  LocalDate date;

@NotBlank
 private  LocalTime time;

@Column(name = "idOXI5")
 private Long idOXI5;

@Transient
 private TrainingSessionRequest trainingsessionrequest = new TrainingSessionRequestImpl();;

public Result(@NotBlank TrainingSession session, Long sessionId, List<Answer> answer, @NotBlank double rightAnswers, @NotBlank LocalDate date, @NotBlank LocalTime time) {
    this.session = session;
    this.sessionId = sessionId;
    this.answer = answer;
    this.rightAnswers = rightAnswers;
    this.date = date;
    this.time = time;
}public Result() {
}
public LocalTime getTime(){
    return time;
}


public void setRightAnswers(double rightAnswers){
    this.rightAnswers = rightAnswers;
}


public Long getId(){
    return id;
}


public boolean containsAnswer(Long id){
    for (Answer ans : answer) {
        if (ans.getId().equals(id))
            return true;
    }
    return false;
}


public void setSessionId(Long sessionId){
    this.sessionId = sessionId;
}


public List<Answer> getAnswer(){
    return answer;
}


public void setAnswer(List<Answer> answer){
    this.answer = answer;
}


public void setId(Long id){
    this.id = id;
}


public void setDate(LocalDate date){
    this.date = date;
}


public LocalDate getDate(){
    return date;
}


public void setSession(TrainingSession session){
this.idOXI5 = session.getSession() ;
trainingsessionrequest.setSession(session,this.idOXI5);
 this.session = session;
}



public Long getSessionId(){
    return sessionId;
}


public double getRightAnswers(){
    return rightAnswers;
}


public void setTime(LocalTime time){
    this.time = time;
}


}