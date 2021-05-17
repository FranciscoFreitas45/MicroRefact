import com.ats.hrmgt.model.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface ContractorRepo extends JpaRepository<Contractor, Integer> {


public Contractor findByContractorIdAndDelStatus(int contractorId,int del)


public List<Contractor> findByCompanyIdAndDelStatusOrderByContractorIdDesc(int companyId,int del)


@Transactional
@Modifying
@Query(value = "UPDATE m_contractor SET del_status = 0 WHERE contractor_id=:contractorId", nativeQuery = true)
public int deleteContractorById(int contractorId)


}