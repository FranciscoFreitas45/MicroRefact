package kielce.tu.weaii.telelearn.servicedata;
 import kielce.tu.weaii.telelearn.models.courses.Course;
import lombok.Data;
import java.time.Duration;
import java.util.Map;
@Data
public class StudentStats {

 private  Duration taskTimeForWeek;

 private  Duration plannedTimeForWeek;

 private  Duration learningTimeForWeek;

 private  Map<Course,Duration> learningTimeForCourseSevenDays;

 private  Map<Course,Duration> learningTimeForCourseTotal;

 private  Map<Integer,Long> hoursLearningStats;

 private  Duration averageLearningTime;


}