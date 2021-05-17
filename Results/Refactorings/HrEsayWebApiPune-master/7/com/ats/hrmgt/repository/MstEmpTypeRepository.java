import com.ats.hrmgt.model.MstEmpType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface MstEmpTypeRepository extends JpaRepository<MstEmpType, Integer> {


@Query(value = "select et.* from tbl_mst_emp_types et,m_employees e where e.emp_type=et.emp_type_id and emp_id=:empId", nativeQuery = true)
public MstEmpType getTypeByempId(int empId)


public MstEmpType findByDelStatusAndEmpTypeId(int i,int empTypeId)


@Transactional
@Modifying
@Query("update MstEmpType set del_status=0  WHERE emp_type_id=:lvTypeId")
public int deleteMstType(int lvTypeId)


public List<MstEmpType> findByDelStatusAndCompanyIdOrderByEmpTypeIdDesc(int i,int companyId)


public List<MstEmpType> findByDelStatusAndCompanyId(int i,int companyId)


}