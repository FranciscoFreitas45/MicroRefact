package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import kielce.tu.weaii.telelearn.views.StudentView;
import kielce.tu.weaii.telelearn.views.TimeVew;
import lombok.Value;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kielce.tu.weaii.telelearn.utilities.Constants.DATE_FORMATTER_FOR_MAP_KEY;
import kielce.tu.weaii.telelearn.utilities.Constants.TIME_FORMATTER;
@Value
public class TaskScheduleView {

 private Long id;

 private LocalDate date;

 private String scheduleTime;

 private String endScheduleTime;

 private TimeVew plannedTime;

 private TimeVew learningTime;

 private StudentView student;

 private TaskView task;

 private Boolean isOverlap;


public Map<String,List<TaskScheduleView>> form(List<TaskScheduleRecord> taskSchedule){
    Map<String, List<TaskScheduleView>> returnMap = new HashMap<>();
    Map<LocalDate, List<TaskScheduleView>> mapWithLocalDate = taskSchedule.stream().map(current -> formOnList(current, checkOverlapList(current, taskSchedule))).collect(Collectors.groupingBy(TaskScheduleView::getDate));
    for (Map.Entry<LocalDate, List<TaskScheduleView>> entry : mapWithLocalDate.entrySet()) {
        returnMap.put(entry.getKey().format(DATE_FORMATTER_FOR_MAP_KEY), entry.getValue());
    }
    return returnMap;
}


public boolean checkOverLap(TaskScheduleRecord current,TaskScheduleRecord record){
    LocalTime currentEnd = current.getScheduleTime().plusMinutes(current.getPlannedTime().toMinutes());
    LocalTime recordEnd = record.getScheduleTime().plusMinutes(record.getPlannedTime().toMinutes());
    return current.getScheduleTime().isBefore(recordEnd) && record.getScheduleTime().isBefore(currentEnd);
}


public TaskScheduleView formOnList(TaskScheduleRecord model,boolean isOverlap){
    return new TaskScheduleView(model.getId(), model.getDate(), (model.getScheduleTime() != null) ? model.getScheduleTime().format(TIME_FORMATTER) : null, (model.getScheduleTime() != null) ? model.getScheduleTime().plusMinutes(model.getPlannedTime().toMinutes()).format(TIME_FORMATTER) : null, TimeVew.form(model.getPlannedTime()), TimeVew.form(model.getLearningTime()), StudentView.from(model.getStudent(), false), TaskViewForStudent.from(model.getTask(), model.getStudent().getId()), isOverlap);
}


public boolean checkOverlapList(TaskScheduleRecord current,List<TaskScheduleRecord> taskSchedule){
    for (TaskScheduleRecord record : taskSchedule) {
        if (!record.equals(current) && checkOverLap(current, record)) {
            return true;
        }
    }
    return false;
}


}