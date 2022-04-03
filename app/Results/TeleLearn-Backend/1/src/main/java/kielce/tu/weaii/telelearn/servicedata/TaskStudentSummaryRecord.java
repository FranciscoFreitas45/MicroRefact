package kielce.tu.weaii.telelearn.servicedata;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import lombok.Data;
import java.time.Duration;
@Data
public class TaskStudentSummaryRecord {

 private  Task task;

 private  Duration totalLearningTime;

 private  Duration totalPlannedLearningTime;


}