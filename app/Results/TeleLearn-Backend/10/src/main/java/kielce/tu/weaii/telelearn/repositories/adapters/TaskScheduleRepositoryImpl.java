package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import kielce.tu.weaii.telelearn.repositories.jpa.TaskScheduleJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.TaskScheduleRepository;
import org.springframework.stereotype.Repository;
@Repository
public class TaskScheduleRepositoryImpl extends BaseCRUDRepositoryImpl<TaskScheduleRecord>implements TaskScheduleRepository{

 private  TaskScheduleJPARepository jpaRepository;

public TaskScheduleRepositoryImpl(TaskScheduleJPARepository jpaRepository) {
    super(jpaRepository);
    this.jpaRepository = jpaRepository;
}
@Override
public void deleteAllStudentRecord(Long studentId){
    jpaRepository.deleteAllStudentRecord(studentId);
}


}