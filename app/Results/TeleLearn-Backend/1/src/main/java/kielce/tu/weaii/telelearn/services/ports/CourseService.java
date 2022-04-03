package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.requests.courses.CourseRequest;
public interface CourseService {


public Course add(CourseRequest request)
;

public void acceptStudent(Long courseId,Long studentId)
;

public Course getById(Long id)
;

public void signOutStudent(Long courseId,Long studentId)
;

public Course update(Long id,CourseRequest request)
;

public Course getCourse(Long id)
;

public void delete(Long id)
;

public boolean signUpStudent(Long courseId,Long studentId)
;

}