import com.ats.hrmgt.model.EmpDoctype;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmpDoctypeRepo extends JpaRepository<EmpDoctype, Integer> {


public List<EmpDoctype> findByDelStatusAndCompanyId(int del,int companyId)


}