package kielce.tu.weaii.telelearn.repositories.ports;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import java.util.List;
public interface TaskRepository extends BaseCRUDRepository<Task>{


public List<Task> getStudentByTasksFromCurse(Long studentId)
;

}