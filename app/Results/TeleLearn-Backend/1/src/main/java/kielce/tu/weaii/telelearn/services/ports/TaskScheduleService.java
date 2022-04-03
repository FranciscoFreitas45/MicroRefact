package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import kielce.tu.weaii.telelearn.requests.courses.RecordLearningRequest;
import kielce.tu.weaii.telelearn.requests.courses.ScheduleTaskRequest;
import kielce.tu.weaii.telelearn.requests.courses.ScheduleUpdateRequest;
import java.time.LocalDate;
import java.util.List;
public interface TaskScheduleService {


public TaskScheduleRecord schedule(ScheduleTaskRequest request,LocalDate today)
;

public List<TaskScheduleRecord> getListForStudent(Long studentId)
;

public TaskScheduleRecord updateSchedule(Long id,ScheduleUpdateRequest request,LocalDate today)
;

public TaskScheduleRecord getById(Long id)
;

public List<TaskScheduleRecord> getListForTaskAndStudent(Long studentId,Long taskId)
;

public void deleteSchedulesForStudent(Long studentId)
;

public TaskScheduleRecord updateLearningTime(Long id,RecordLearningRequest request,LocalDate today)
;

public void delete(Long id)
;

}