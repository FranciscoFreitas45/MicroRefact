package lk.sliit.csse.procurementsystem.repositories;
 import lk.sliit.csse.procurementsystem.models.Employee;
import lk.sliit.csse.procurementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface EmployeeBaseRepository extends UserBaseRepository<T>{


public Employee findByEmail(String email)
;

}