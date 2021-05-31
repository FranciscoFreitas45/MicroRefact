import com.ats.hrmgt.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface DesignationRepo extends JpaRepository<Designation, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE m_designation SET del_status=0 WHERE desig_id=:desigId", nativeQuery = true)
public int deleteDesignation(int desigId)


public List<Designation> findByCompanyIdAndDelStatusOrderByDesigIdDesc(int companyId,int del)


public List<Designation> findByNameAndCompanyId(String desgn,int compId)


public List<Designation> findByNameAndCompanyIdAndDesigIdNot(String trim,int compId,int primaryKey)


public List<Designation> findByCompanyIdAndDelStatusOrderByExInt1Asc(int companyId,int i)


public Designation findByDesigIdAndDelStatus(int desigId,int del)


}