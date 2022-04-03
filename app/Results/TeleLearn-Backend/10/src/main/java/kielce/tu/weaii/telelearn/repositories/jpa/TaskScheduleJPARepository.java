package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface TaskScheduleJPARepository extends JpaRepository<TaskScheduleRecord, Long>{


@Query("delete from TaskScheduleRecord ts where ts.student.id = ?1")
@Modifying
public void deleteAllStudentRecord(Long studentId)
;

}