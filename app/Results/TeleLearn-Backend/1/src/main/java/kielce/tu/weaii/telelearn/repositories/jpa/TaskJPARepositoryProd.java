package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Profile("prod")
public interface TaskJPARepositoryProd extends TaskJPARepository{


@Query(value = "SELECT * FROM GET_STUDENT_TASK(?1)", nativeQuery = true)
public List<Task> getStudentByTasksFromCurse(Long studentId)
;

}