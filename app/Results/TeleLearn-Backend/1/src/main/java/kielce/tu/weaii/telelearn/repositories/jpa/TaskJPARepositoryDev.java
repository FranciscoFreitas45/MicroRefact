package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Profile("!prod")
public interface TaskJPARepositoryDev extends TaskJPARepository{


@Query("SELECT t FROM Task t INNER JOIN t.course.students s WHERE s.student.id = ?1 AND s.accepted = true")
public List<Task> getStudentByTasksFromCurse(Long studentId)
;

}