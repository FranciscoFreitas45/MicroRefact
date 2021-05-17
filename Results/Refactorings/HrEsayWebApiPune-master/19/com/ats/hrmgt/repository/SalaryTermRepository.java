import com.ats.hrmgt.model.SalaryTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface SalaryTermRepository extends JpaRepository<SalaryTerm, Integer> {


@Query(value = "select * from tbl_sal_terms  order by sal_type_id,sort_order asc", nativeQuery = true)
public List<SalaryTerm> getSalaryTermList()


}