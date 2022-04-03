package lk.sliit.csse.procurementsystem.repositories;
 import lk.sliit.csse.procurementsystem.models.Employee;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface EmployeeRepository extends EmployeeBaseRepository<Employee>{


}