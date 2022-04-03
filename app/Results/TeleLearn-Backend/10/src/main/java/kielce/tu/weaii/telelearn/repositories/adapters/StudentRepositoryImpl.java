package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.Student;
import kielce.tu.weaii.telelearn.repositories.jpa.StudentJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.StudentRepository;
import org.springframework.stereotype.Repository;
@Repository
public class StudentRepositoryImpl extends BaseCRUDRepositoryImpl<Student>implements StudentRepository{

public StudentRepositoryImpl(StudentJPARepository jpaRepository) {
    super(jpaRepository);
}
}