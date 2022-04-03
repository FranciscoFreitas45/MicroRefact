package lk.sliit.csse.procurementsystem.services;
 import lk.sliit.csse.procurementsystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service("employeeService")
public class EmployeeService {

@Autowired
 private  EmployeeRepository employeeRepository;

 private  BCryptPasswordEncoder bCryptPasswordEncoder;


}