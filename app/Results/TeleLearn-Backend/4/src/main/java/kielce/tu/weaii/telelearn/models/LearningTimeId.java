package kielce.tu.weaii.telelearn.models;
 import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
@Data
@Embeddable
public class LearningTimeId implements Serializable{

@Column(name = "STUDENT_ID")
 private  Long studentId;

@Column(name = "DATE")
 private  LocalDate date;


}