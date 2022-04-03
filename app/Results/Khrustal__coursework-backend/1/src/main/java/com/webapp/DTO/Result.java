package com.webapp.DTO;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.webapp.Request.TrainingSessionRequest;
import com.webapp.Request.Impl.TrainingSessionRequestImpl;
import com.webapp.DTO.TrainingSession;
public class Result {

 private  Long id;

 private  TrainingSession session;

 private  Long sessionId;

 private  List<Answer> answer;

 private  double rightAnswers;

 private  LocalDate date;

 private  LocalTime time;

 private Long id;

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


public Long getId(){
    return id;
}


public List<Answer> getAnswer(){
    return answer;
}


public LocalDate getDate(){
    return date;
}


public Long getSessionId(){
    return sessionId;
}


public double getRightAnswers(){
    return rightAnswers;
}


}