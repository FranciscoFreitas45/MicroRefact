package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.Student;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.requests.StudentRegisterRequest;
import kielce.tu.weaii.telelearn.requests.StudentUpdateRequest;
import java.util.List;
public interface StudentService {


public Student add(StudentRegisterRequest request)
;

public List<Course> getCourses(Long id)
;

public Student getById(Long id)
;

public Student update(Long id,StudentUpdateRequest request)
;

public void delete(Long id)
;

}