package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.Teacher;
import kielce.tu.weaii.telelearn.repositories.jpa.TeacherJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.TeacherRepository;
import org.springframework.stereotype.Repository;
@Repository
public class TeacherRepositoryImpl extends BaseCRUDRepositoryImpl<Teacher>implements TeacherRepository{

public TeacherRepositoryImpl(TeacherJPARepository jpaRepository) {
    super(jpaRepository);
}
}