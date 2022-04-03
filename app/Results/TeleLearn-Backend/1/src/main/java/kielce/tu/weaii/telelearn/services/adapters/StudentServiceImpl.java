package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.users.UserNotFoundException;
import kielce.tu.weaii.telelearn.models.Student;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.models.courses.CourseStudent;
import kielce.tu.weaii.telelearn.repositories.ports.StudentRepository;
import kielce.tu.weaii.telelearn.requests.StudentRegisterRequest;
import kielce.tu.weaii.telelearn.requests.StudentUpdateRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.services.ports.StudentService;
import kielce.tu.weaii.telelearn.services.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import kielce.tu.weaii.telelearn.models.Student.DEFAULT_DAILY_LEARNING_TIME;
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

 private  UserService userService;

 private  UserServiceDetailsImpl userServiceDetails;

 private  StudentRepository studentRepository;

 private  ModelMapper modelMapper;

 private  PasswordEncoder passwordEncoder;


@Transactional
public Student add(StudentRegisterRequest request){
    userService.checkAvailability(request.getUsername(), request.getEmail());
    Student model = modelMapper.map(request, Student.class);
    model.setPassword(passwordEncoder.encode(String.valueOf(model.getPassword())).toCharArray());
    model.setUserRole(UserRole.STUDENT);
    model.setEnabled(true);
    model.setDailyLearningTime(DEFAULT_DAILY_LEARNING_TIME);
    return studentRepository.save(model);
}


@Override
public List<Course> getCourses(Long id){
    if (!userService.isCurrentUserOrAdmin(id)) {
        throw new AuthorizationException("lista kursów użytkownnika", id, userServiceDetails.getCurrentUser().getId());
    }
    return getById(id).getCourses().stream().filter(CourseStudent::isAccepted).map(CourseStudent::getCourse).collect(Collectors.toList());
}


public Student getById(Long id){
    return studentRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id, UserRole.STUDENT));
}


@Transactional
public Student update(Long id,StudentUpdateRequest request){
    if (!userService.isCurrentUserOrAdmin(id)) {
        throw new AuthorizationException("użytkownik", null, id);
    }
    Student student = getById(id);
    if (!student.getEmail().equals(request.getEmail())) {
        userService.checkAvailability(request.getEmail());
    }
    BeanUtils.copyProperties(request, student);
    return studentRepository.save(student);
}


@Transactional
public void delete(Long id){
    studentRepository.delete(getById(id));
}


}