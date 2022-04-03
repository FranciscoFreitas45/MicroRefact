package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.courses.CourseNotFoundException;
import kielce.tu.weaii.telelearn.exceptions.courses.StudentAlreadyEnrolled;
import kielce.tu.weaii.telelearn.exceptions.courses.StudentOnListNotFound;
import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.models.courses.CourseStudent;
import kielce.tu.weaii.telelearn.models.courses.Task;
import kielce.tu.weaii.telelearn.repositories.ports.CourseRepository;
import kielce.tu.weaii.telelearn.requests.courses.CourseRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.services.ports;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

 private  CourseRepository repository;

 private  UserServiceDetailsImpl userServiceDetails;

 private  TeacherService teacherService;

 private  StudentService studentService;

 private  UserService userService;

@Autowired
@Setter
 private  TaskScheduleService taskScheduleService;


@Override
@Transactional
public Course add(CourseRequest request){
    checkAuthorization(request);
    Course course = new Course();
    BeanUtils.copyProperties(request, course);
    course.setOwner(teacherService.getById(request.getOwnerId()));
    return repository.save(course);
}


@Override
public void acceptStudent(Long courseId,Long studentId){
    Course course = getCourse(courseId);
    checkCourseAuthorization(courseId, course.getOwner().getId());
    CourseStudent courseStudentEntry = getCourseStudentEntry(courseId, studentId, course);
    courseStudentEntry.setAccepted(true);
    repository.save(course);
}


@Override
public void signOutStudent(Long courseId,Long studentId){
    Course course = getCourse(courseId);
    checkDeletingUserAuthorization(courseId, studentId, course);
    CourseStudent courseStudentEntry = getCourseStudentEntry(courseId, studentId, course);
    course.getStudents().remove(courseStudentEntry);
    for (Task task : course.getTasks()) {
        task.getStudents().removeIf(entry -> entry.getStudent().getId().equals(studentId));
    }
    taskScheduleService.deleteSchedulesForStudent(studentId);
    repository.save(course);
}


@Override
public Course update(Long id,CourseRequest request){
    checkAuthorization(request);
    Course course = getById(id);
    BeanUtils.copyProperties(request, course);
    checkCourseAuthorization(id, course.getOwner().getId());
    if (!request.getOwnerId().equals(course.getOwner().getId())) {
        course.setOwner(teacherService.getById(request.getOwnerId()));
    }
    return repository.save(course);
}


@Override
public Course getCourse(Long id){
    return repository.getById(id).orElseThrow(() -> new CourseNotFoundException(id));
}


@Override
@Transactional
public void delete(Long id){
    Course course = getById(id);
    checkCourseAuthorization(id, course.getOwner().getId());
    List<Long> studentIds = course.getStudents().stream().map(e -> e.getStudent().getId()).collect(Collectors.toList());
    for (long studentId : studentIds) {
        signOutStudent(course.getId(), studentId);
    }
    repository.delete(course);
}


public void checkCourseAuthorization(Long courseId,Long ownerId){
    User currentUser = userServiceDetails.getCurrentUser();
    if (!currentUser.getUserRole().equals(UserRole.ADMIN) && !currentUser.getId().equals(ownerId)) {
        throw new AuthorizationException("kurs", currentUser.getId(), courseId);
    }
}


@Override
public Course getById(Long id){
    Course course = getCourse(id);
    User currentUser = userServiceDetails.getCurrentUser();
    if (!userService.isCurrentUserOrAdmin(course.getOwner().getId()) && course.getStudents().stream().filter(CourseStudent::isAccepted).noneMatch(entry -> entry.getStudent().getId().equals(currentUser.getId()))) {
        throw new AuthorizationException("kurs", currentUser.getId(), id);
    }
    return course;
}


public CourseStudent getCourseStudentEntry(Long courseId,Long studentId,Course course){
    return course.getStudents().stream().filter(entry -> entry.getStudent().getId().equals(studentId)).findAny().orElseThrow(() -> new StudentOnListNotFound(courseId, studentId));
}


public void checkDeletingUserAuthorization(Long courseId,Long studentId,Course course){
    User currentUser = userServiceDetails.getCurrentUser();
    if (!currentUser.getUserRole().equals(UserRole.ADMIN) && !currentUser.getId().equals(course.getOwner().getId()) && !currentUser.getId().equals(studentId)) {
        throw new AuthorizationException("usuwanie użytkownika z kursu", currentUser.getId(), courseId);
    }
}


public CourseStudent prepareStudentOnCourseEntry(Course course,Long studentId){
    CourseStudent courseStudent = new CourseStudent();
    courseStudent.setStudent(studentService.getById(studentId));
    courseStudent.setCourse(course);
    courseStudent.setAccepted(course.isAutoAccept());
    course.getStudents().add(courseStudent);
    repository.save(course);
    return courseStudent;
}


public void checkUserAuthorization(Long studentId){
    User currentUser = userServiceDetails.getCurrentUser();
    if (!userService.isCurrentUserOrAdmin(studentId)) {
        throw new AuthorizationException("zapisywanie użytkownika", currentUser.getId(), studentId);
    }
}


@Override
public boolean signUpStudent(Long courseId,Long studentId){
    checkUserAuthorization(studentId);
    Course course = getCourse(courseId);
    if (course.getStudents().stream().anyMatch(entry -> entry.getStudent().getId().equals(studentId))) {
        throw new StudentAlreadyEnrolled();
    }
    CourseStudent courseStudent = prepareStudentOnCourseEntry(course, studentId);
    return courseStudent.isAccepted();
}


public void checkAuthorization(CourseRequest request){
    if (!userService.isCurrentUserOrAdmin(request.getOwnerId())) {
        throw new AuthorizationException("dodawanie/edycja kursu", userServiceDetails.getCurrentUser().getId(), request.getOwnerId());
    }
}


}