package com.bau.graduateprojects.qrstudentsattendance.auth0;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentEntity;
import com.bau.graduateprojects.qrstudentsattendance.repositories.student.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Service("StudentService")
public class JwtUserDetailsService implements UserDetailsService{

 private  StudentRepository studentRepository;

public JwtUserDetailsService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
}
@Override
public UserDetails loadUserByUsername(String username){
    StudentEntity student = studentRepository.getByUsername(username);
    if (student == null) {
        throw new UsernameNotFoundException("Student not found with username: " + username);
    }
    return student;
}


}