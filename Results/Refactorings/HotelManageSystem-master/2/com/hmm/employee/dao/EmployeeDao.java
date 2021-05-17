import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.employee.entity.Employee;
@Repository
public interface EmployeeDao extends PagingAndSortingRepository<Employee, Integer> {


@Modifying
@Query("update Employee e set e.password = ?1 where e.userName = ?2")
public void updatePassword(String password,String userName)


public Employee findByEmpName(String empName)


public Employee findByUserName(String userName)


@Query("from Employee o where o.empName = ?1 and o.empNo = ?2")
public Employee findByEmpNameAndEmpNo(String empName,String empNo)


@Query("from Employee o where o.empNo = ?1")
public Employee findByEmpNo(String empNo)


}