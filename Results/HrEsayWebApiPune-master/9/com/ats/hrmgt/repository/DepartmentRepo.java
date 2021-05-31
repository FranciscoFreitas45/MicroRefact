import com.ats.hrmgt.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface DepartmentRepo extends JpaRepository<Department, Integer> {


public Department findByDepartIdAndDelStatus(int deptId,int del)


public List<Department> findByCompanyIdAndDelStatusOrderByDepartIdDesc(int companyId,int del)


public List<Department> findByNameAndCompanyId(String dept,int compId)


@Query(value = " SELECT  * FROM  m_department WHERE del_status=1 AND   depart_id IN (:deptIdString) ", nativeQuery = true)
public List<Department> findByDepartIdIn(List<Integer> deptIdString)


@Transactional
@Modifying
@Query(value = "UPDATE m_department SET del_status=0 WHERE depart_id=:deptId", nativeQuery = true)
public int deleteDepartment(int deptId)


public List<Department> findByNameAndCompanyIdAndDepartIdNot(String trim,int compId,int primaryKey)


}