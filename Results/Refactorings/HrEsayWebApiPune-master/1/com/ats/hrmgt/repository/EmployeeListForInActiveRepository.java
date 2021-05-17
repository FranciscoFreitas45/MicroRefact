import com.ats.hrmgt.model.EmployeeListForInActive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface EmployeeListForInActiveRepository extends JpaRepository<EmployeeListForInActive, Integer> {


@Query(value = "SELECT e.* from m_employees e  where e.notice_pay_amount=1", nativeQuery = true)
public List<EmployeeListForInActive> getEmpInactiveList()


}