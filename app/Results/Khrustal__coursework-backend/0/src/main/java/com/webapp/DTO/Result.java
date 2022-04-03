package com.webapp.DTO;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
public class Result {

 private  Long id;

 private  TrainingSession session;

 private  Long sessionId;

 private  List<Answer> answer;

 private  double rightAnswers;

 private  LocalDate date;

 private  LocalTime time;

 private Long idOXI5;

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