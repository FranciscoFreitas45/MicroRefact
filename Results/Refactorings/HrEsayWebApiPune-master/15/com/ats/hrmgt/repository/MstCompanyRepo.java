import com.ats.hrmgt.model.MstCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MstCompanyRepo extends JpaRepository<MstCompany, Integer> {


public List<MstCompany> findByDelStatusOrderByCompanyIdDesc(int del)


public MstCompany findByCompanyIdAndDelStatus(int companyId,int del)


}