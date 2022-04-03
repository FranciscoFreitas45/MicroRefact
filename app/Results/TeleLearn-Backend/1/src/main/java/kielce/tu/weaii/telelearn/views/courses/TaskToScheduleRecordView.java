package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.servicedata.TaskStudentSummaryRecord;
import kielce.tu.weaii.telelearn.views.TimeVew;
import lombok.Value;
@Value
public class TaskToScheduleRecordView {

 private TaskViewForStudent task;

 private TimeVew totalLearningTime;

 private TimeVew totalPlannedLearningTime;


public TaskToScheduleRecordView from(TaskStudentSummaryRecord record,long studentId){
    return new TaskToScheduleRecordView(TaskViewForStudent.from(record.getTask(), studentId), TimeVew.form(record.getTotalLearningTime()), TimeVew.form(record.getTotalPlannedLearningTime()));
}


}