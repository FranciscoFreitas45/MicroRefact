package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.servicedata.StudentStats;
import lombok.Value;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@Value
public class StudentStatsView {

 private TimeVew taskTimeForWeek;

 private TimeVew plannedTimeForWeek;

 private TimeVew learningTimeForWeek;

 private Set<Map<String,Object>> learningTimeForCourseSevenDays;

 private Set<Map<String,Object>> learningTimeForCourseTotal;

 private Set<Map<String,Number>> hoursLearningStats;

 private TimeVew averageLearningTime;


public Set<Map<String,Number>> convertHoursLearningStats(Map<Integer,Long> hoursLearningStats){
    return hoursLearningStats.entrySet().stream().map(StudentStatsView::convertHourStat).collect(Collectors.toSet());
}


public StudentStatsView from(StudentStats studentStats){
    Set<Map<String, Object>> learningTimeForCourseSevenDays = new HashSet<>();
    Set<Map<String, Object>> learningTimeForCourseTotal = new HashSet<>();
    for (Map.Entry<Course, Duration> entry : studentStats.getLearningTimeForCourseSevenDays().entrySet()) {
        converMap(learningTimeForCourseSevenDays, entry);
    }
    for (Map.Entry<Course, Duration> entry : studentStats.getLearningTimeForCourseTotal().entrySet()) {
        converMap(learningTimeForCourseTotal, entry);
    }
    return new StudentStatsView(TimeVew.form(studentStats.getTaskTimeForWeek()), TimeVew.form(studentStats.getPlannedTimeForWeek()), TimeVew.form(studentStats.getLearningTimeForWeek()), learningTimeForCourseSevenDays, learningTimeForCourseTotal, convertHoursLearningStats(studentStats.getHoursLearningStats()), TimeVew.form(studentStats.getAverageLearningTime()));
}


public Map<String,Number> convertHourStat(Map.Entry<Integer,Long> statEntry){
    Map<String, Number> newStatEntry = new HashMap<>();
    newStatEntry.put("hour", statEntry.getKey());
    newStatEntry.put("learningTimes", statEntry.getValue());
    return newStatEntry;
}


public void converMap(Set<Map<String,Object>> learningTimeForCourseSevenDays,Map.Entry<Course,Duration> entry){
    Map<String, Object> statMap = new HashMap<>();
    statMap.put("course", entry.getKey() != null ? entry.getKey().getName() : "usunięte");
    statMap.put("learningTime", TimeVew.form(entry.getValue()));
    learningTimeForCourseSevenDays.add(statMap);
}


}