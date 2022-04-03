package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.models.StudentStatsRecord;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.models.courses.Task;
import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import kielce.tu.weaii.telelearn.repositories.ports.CourseRepository;
import kielce.tu.weaii.telelearn.repositories.ports.StudentStatsRepository;
import kielce.tu.weaii.telelearn.repositories.ports.TaskRepository;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.servicedata.StudentStats;
import kielce.tu.weaii.telelearn.services.ports.StudentStatsService;
import kielce.tu.weaii.telelearn.services.ports.TaskScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kielce.tu.weaii.telelearn.utilities.Constants.WEEK_END_RANGE_DAYS;
@Service
@RequiredArgsConstructor
public class StudentStatsServiceImpl implements StudentStatsService{

 private  StudentStatsRepository studentStatsRepository;

 private  UserServiceDetailsImpl userServiceDetails;

 private  TaskRepository taskRepository;

 private  CourseRepository courseRepository;

@Autowired
@Setter
 private  TaskScheduleService taskScheduleService;


public Map<Course,Duration> getLearningTimeForSevenDays(LocalDate today,List<StudentStatsRecord> studentStats){
    return studentStats.stream().filter(schedule -> weekFilter(today.minusDays(WEEK_END_RANGE_DAYS), today.plusDays(1), schedule.getDate())).collect(Collectors.toMap(record -> courseRepository.getById(record.getCourseId()).orElse(null), StudentStatsRecord::getLearningTime, Duration::plus));
}


public Map<Course,Duration> getLearningTimeForCourseTotal(List<StudentStatsRecord> studentStats){
    return studentStats.stream().collect(Collectors.toMap(record -> courseRepository.getById(record.getCourseId()).orElse(null), StudentStatsRecord::getLearningTime, Duration::plus));
}


@Override
@Transactional
public void recordOrUpdateLearning(TaskScheduleRecord taskScheduleRecord,LocalTime startTime){
    StudentStatsRecord record = studentStatsRepository.getByScheduleId(taskScheduleRecord.getId()).orElseGet(() -> getNewRecord(taskScheduleRecord));
    record.setStartTime(startTime);
    record.setLearningTime(taskScheduleRecord.getLearningTime());
    studentStatsRepository.save(record);
}


public Duration getPlannedTime(LocalDate begin,LocalDate end,List<TaskScheduleRecord> studentSchedule){
    return studentSchedule.stream().filter(record -> weekFilter(begin, end, record.getDate())).map(TaskScheduleRecord::getPlannedTime).reduce(Duration.ZERO, Duration::plus);
}


public Duration getLearningTime(LocalDate begin,LocalDate end,List<StudentStatsRecord> studentStats){
    return studentStats.stream().filter(record -> weekFilter(begin, end, record.getDate())).map(StudentStatsRecord::getLearningTime).reduce(Duration.ZERO, Duration::plus);
}


public StudentStatsRecord getNewRecord(TaskScheduleRecord taskScheduleRecord){
    StudentStatsRecord record = new StudentStatsRecord();
    record.setCourseId(taskScheduleRecord.getTask().getCourse().getId());
    record.setScheduleId(taskScheduleRecord.getId());
    record.setStudent(taskScheduleRecord.getStudent());
    record.setDate(taskScheduleRecord.getDate());
    return record;
}


public boolean weekFilter(LocalDate begin,LocalDate end,LocalDate date){
    return date.isBefore(end) && date.isAfter(begin);
}


public Duration getTaskLearningTime(LocalDate begin,LocalDate end,List<Task> studentTasks){
    return studentTasks.stream().filter(task -> weekFilter(begin, end, task.getDueDate())).map(Task::getLearningTime).reduce(Duration.ZERO, Duration::plus);
}


public StudentStats getStudentStats(Long studentId,LocalDate today){
    LocalDate filterBeginDate = today.minusDays(today.getDayOfWeek().getValue());
    LocalDate filterEndDate = filterBeginDate.plusDays(WEEK_END_RANGE_DAYS);
    List<TaskScheduleRecord> studentSchedule = taskScheduleService.getListForStudent(studentId);
    List<Task> studentTasks = taskRepository.getStudentByTasksFromCurse(studentId);
    List<StudentStatsRecord> studentStatList = studentStatsRepository.getStudentStat(studentId);
    StudentStats studentStats = new StudentStats();
    studentStats.setAverageLearningTime(getAverageLearningTime(studentStatList));
    studentStats.setLearningTimeForWeek(getLearningTime(filterBeginDate, filterEndDate, studentStatList));
    studentStats.setPlannedTimeForWeek(getPlannedTime(filterBeginDate, filterEndDate, studentSchedule));
    studentStats.setTaskTimeForWeek(getTaskLearningTime(filterBeginDate, filterEndDate, studentTasks));
    studentStats.setLearningTimeForCourseTotal(getLearningTimeForCourseTotal(studentStatList));
    studentStats.setLearningTimeForCourseSevenDays(getLearningTimeForSevenDays(today, studentStatList));
    studentStats.setHoursLearningStats(getHoursLearningStats(studentStatList));
    return studentStats;
}


@Override
public StudentStats getStudentStat(Long studentId,LocalDate today){
    if (!userServiceDetails.getCurrentUser().getId().equals(studentId)) {
        throw new AuthorizationException("Statystyki ucznia.", userServiceDetails.getCurrentUser().getId(), studentId);
    }
    return getStudentStats(studentId, today);
}


public Map<Integer,Long> getHoursLearningStats(List<StudentStatsRecord> studentStats){
    return studentStats.stream().collect(Collectors.groupingBy(record -> record.getStartTime().getHour(), Collectors.counting()));
}


public Duration getAverageLearningTime(List<StudentStatsRecord> records){
    long averageMinutes = Math.round(records.stream().map(StudentStatsRecord::getLearningTime).mapToLong(Duration::toMinutes).average().orElse(0.0));
    return Duration.ofMinutes(averageMinutes);
}


@Override
@Transactional
public void deleteRecord(TaskScheduleRecord taskScheduleRecord){
    studentStatsRepository.getByScheduleId(taskScheduleRecord.getId()).ifPresent(studentStatsRepository::delete);
}


}