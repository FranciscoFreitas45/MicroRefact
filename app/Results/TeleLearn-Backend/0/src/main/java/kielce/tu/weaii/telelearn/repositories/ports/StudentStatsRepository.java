package kielce.tu.weaii.telelearn.repositories.ports;
 import kielce.tu.weaii.telelearn.models.StudentStatsRecord;
import java.util.List;
import java.util.Optional;
public interface StudentStatsRepository {


public List<StudentStatsRecord> getStudentStat(Long studentId)
;

public Optional<StudentStatsRecord> getByScheduleId(Long id)
;

public StudentStatsRecord save(StudentStatsRecord record)
;

public void delete(StudentStatsRecord record)
;

}