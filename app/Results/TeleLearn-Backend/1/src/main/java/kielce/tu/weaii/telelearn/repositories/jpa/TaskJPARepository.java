package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
@NoRepositoryBean
public interface TaskJPARepository extends JpaRepository<Task, Long>{


public List<Task> getStudentByTasksFromCurse(Long studentId)
;

}