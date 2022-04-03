package kielce.tu.weaii.telelearn.DTO;
 import lombok.Data;
import javax.persistence;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
public class LearningTime implements Serializable{

 private  LearningTimeId id;

 private  Student student;

 private  LocalDate date;

 private  Duration time;

 private Long id;


}