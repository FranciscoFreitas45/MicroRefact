package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import kielce.tu.weaii.telelearn.repositories.jpa.TaskJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.TaskRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class TaskRepositoryImpl extends BaseCRUDRepositoryImpl<Task>implements TaskRepository{

 private  TaskJPARepository jpaRepository;

public TaskRepositoryImpl(TaskJPARepository jpaRepository) {
    super(jpaRepository);
    this.jpaRepository = jpaRepository;
}
@Override
public List<Task> getStudentByTasksFromCurse(Long studentId){
    return jpaRepository.getStudentByTasksFromCurse(studentId);
}


}