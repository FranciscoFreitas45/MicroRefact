package kielce.tu.weaii.telelearn.DTO;
 import kielce.tu.weaii.telelearn.models.courses.CourseStudent;
import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import kielce.tu.weaii.telelearn.models.courses.TaskStudent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Duration;
import java.util.List;
import javax.persistence.CascadeType.ALL;
public class Student extends User{

 private  Duration dailyLearningTime;

 private  String unit;

 private  List<CourseStudent> courses;

 private  List<TaskStudent> tasks;

 private  List<TaskScheduleRecord> planRecords;

 private  List<LearningTime> learningTime;

 public  Duration DEFAULT_DAILY_LEARNING_TIME;


}