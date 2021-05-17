import com.ats.hrmgt.model.InoutTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface InoutTableRepo extends JpaRepository<InoutTable, Integer> {


@Transactional
@Modifying
@Query(value = "update HRRAWINOUTDATA set VerMode=1  WHERE id in (:ids)", nativeQuery = true)
public int updatethumbflag(List<Integer> ids)


}