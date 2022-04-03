package kielce.tu.weaii.telelearn.servicedata;
 import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Data
public class TaskStudentSummary {

 private  List<TaskStudentSummaryRecord> delayedTasks;

 private  List<TaskStudentSummaryRecord> taskToRepeat;

 private  Map<LocalDate,List<TaskStudentSummaryRecord>> tasksForDay;


}