package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.TeacherJPARepository;
import kielce.tu.weaii.telelearn.models.Teacher;
@Service
public class TeacherCourseService {

@Autowired
 private TeacherJPARepository teacherjparepository;


}