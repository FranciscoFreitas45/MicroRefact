import com.ats.hrmgt.model.Allowances;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AllowancesRepo extends JpaRepository<Allowances, Integer> {


public Allowances findByShortNameAndDelStatus(String string,int i)


public List<Allowances> findBydelStatusAndIsActive(int del,int active)


public List<Allowances> findBydelStatus(int del)


}