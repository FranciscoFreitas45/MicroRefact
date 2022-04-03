package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.CourseJPARepository;
import kielce.tu.weaii.telelearn.models.courses.Course;
@Service
public class CourseTeacherService {

@Autowired
 private CourseJPARepository coursejparepository;


}