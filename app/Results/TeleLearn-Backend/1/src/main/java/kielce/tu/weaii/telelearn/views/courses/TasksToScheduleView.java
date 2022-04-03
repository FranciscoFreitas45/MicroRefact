package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.servicedata.TaskStudentSummary;
import lombok.experimental.UtilityClass;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kielce.tu.weaii.telelearn.utilities.Constants.DATE_FORMATTER_FOR_MAP_KEY;
@UtilityClass
public class TasksToScheduleView {


public Map<String,List<TaskToScheduleRecordView>> from(TaskStudentSummary scheme,long studentId){
    Map<String, List<TaskToScheduleRecordView>> map = new HashMap<>();
    map.put("delayedTask", scheme.getDelayedTasks().stream().map(record -> TaskToScheduleRecordView.from(record, studentId)).collect(Collectors.toList()));
    map.put("taskToRepeat", scheme.getTaskToRepeat().stream().map(record -> TaskToScheduleRecordView.from(record, studentId)).collect(Collectors.toList()));
    for (LocalDate key : scheme.getTasksForDay().keySet()) {
        String newKey = key.format(DATE_FORMATTER_FOR_MAP_KEY);
        map.put(newKey, scheme.getTasksForDay().get(key).stream().map(record -> TaskToScheduleRecordView.from(record, studentId)).collect(Collectors.toList()));
    }
    return map;
}


}