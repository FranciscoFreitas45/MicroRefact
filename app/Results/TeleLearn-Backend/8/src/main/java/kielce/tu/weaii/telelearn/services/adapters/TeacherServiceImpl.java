package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.users.UserNotFoundException;
import kielce.tu.weaii.telelearn.models.Teacher;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.repositories.ports.TeacherRepository;
import kielce.tu.weaii.telelearn.requests.TeacherRegisterRequest;
import kielce.tu.weaii.telelearn.requests.TeacherUpdateRequest;
import kielce.tu.weaii.telelearn.services.ports.TeacherService;
import kielce.tu.weaii.telelearn.services.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService{

 private  UserService userService;

 private  TeacherRepository teacherRepository;

 private  ModelMapper modelMapper;

 private  PasswordEncoder passwordEncoder;


@Transactional
public Teacher add(TeacherRegisterRequest request){
    userService.checkAvailability(request.getUsername(), request.getEmail());
    Teacher model = modelMapper.map(request, Teacher.class);
    model.setPassword(passwordEncoder.encode(String.valueOf(model.getPassword())).toCharArray());
    model.setUserRole(UserRole.TEACHER);
    model.setEnabled(true);
    return teacherRepository.save(model);
}


public List<Teacher> getAll(){
    return teacherRepository.getAll();
}


@Override
public List<Course> getCourses(Long id){
    if (userService.isCurrentUserOrAdmin(id)) {
        return getById(id).getCourses();
    }
    return getById(id).getCourses().stream().filter(Course::isPublicCourse).collect(Collectors.toList());
}


public Teacher getById(Long id){
    return teacherRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id, UserRole.TEACHER));
}


public Teacher update(Long id,TeacherUpdateRequest request){
    if (!userService.isCurrentUserOrAdmin(id)) {
        throw new AuthorizationException("użytkownik", null, id);
    }
    Teacher teacher = getById(id);
    if (!teacher.getEmail().equals(request.getEmail())) {
        userService.checkAvailability(request.getEmail());
    }
    BeanUtils.copyProperties(request, teacher);
    return teacherRepository.save(teacher);
}


@Transactional
@Override
public void delete(Long id){
    teacherRepository.delete(getById(id));
}


}