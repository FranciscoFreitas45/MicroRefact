package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.Teacher;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.requests.TeacherRegisterRequest;
import kielce.tu.weaii.telelearn.requests.TeacherUpdateRequest;
import java.util.List;
public interface TeacherService {


public Teacher add(TeacherRegisterRequest request)
;

public List<Teacher> getAll()
;

public List<Course> getCourses(Long id)
;

public Teacher getById(Long id)
;

public Teacher update(Long id,TeacherUpdateRequest request)
;

public void delete(Long id)
;

}