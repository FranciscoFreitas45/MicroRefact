package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import kielce.tu.weaii.telelearn.servicedata.StudentStats;
import java.time.LocalDate;
import java.time.LocalTime;
public interface StudentStatsService {


public StudentStats getStudentStat(Long studentId,LocalDate today)
;

public void recordOrUpdateLearning(TaskScheduleRecord taskScheduleRecord,LocalTime startTime)
;

public void deleteRecord(TaskScheduleRecord taskScheduleRecord)
;

}