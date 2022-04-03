package br.com.fatecmogidascruzes.employee.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import br.com.fatecmogidascruzes.course.Course;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.employee.Employee;
import br.com.fatecmogidascruzes.service.BaseService;
public interface EmployeeService extends BaseService<Employee, Long>{


public List<Employee> getEnabledByCourse(Course course)
;

public Page<Employee> getEnabledByFilter(SearchCriteria searchCriteria)
;

public List<Employee> getEnabledProfessors()
;

}