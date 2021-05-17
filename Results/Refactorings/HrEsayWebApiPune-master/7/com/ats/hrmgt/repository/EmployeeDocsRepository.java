import com.ats.hrmgt.model.EmployeDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface EmployeeDocsRepository extends JpaRepository<EmployeDoc, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE emp_docs SET del_status=1 WHERE emp_id=:empId", nativeQuery = true)
public int deleteEmpDoc(int empId)


public List<EmployeDoc> findByDelStatus(int i)


public EmployeDoc findByDocIdAndDelStatus(int docId,int i)


public List<EmployeDoc> findByEmpIdAndDelStatus(int empId,int del)


}