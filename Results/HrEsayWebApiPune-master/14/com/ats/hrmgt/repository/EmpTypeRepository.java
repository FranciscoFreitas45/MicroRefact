import com.ats.hrmgt.model.EmpType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface EmpTypeRepository extends JpaRepository<EmpType, Integer> {


public List<EmpType> findByDelStatus(int i)


@Transactional
@Modifying
@Query("update EmpType set del_status=0  WHERE emp_type_id=:empTypeId")
public int deleteEmpType(int empTypeId)


public EmpType findByEmpTypeIdAndDelStatus(int empTypeId,int i)


public List<EmpType> findByDelStatusOrderByEmpTypeIdDesc(int i)


public List<EmpType> findByDelStatusAndCompanyIdOrderByEmpTypeIdDesc(int i,int compId)


public List<EmpType> findByDelStatusAndCompanyId(int i,int compId)


}