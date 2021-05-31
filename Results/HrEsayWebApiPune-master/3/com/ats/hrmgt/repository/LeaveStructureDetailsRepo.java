import com.ats.hrmgt.model.LeaveStructureDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface LeaveStructureDetailsRepo extends JpaRepository<LeaveStructureDetails, Integer> {


public List<LeaveStructureDetails> findByLvsIdAndDelStatus(int lvsId,int i)


@Query(value = "SELECT\n" + "    *\n" + "FROM\n" + "    leave_structure_details\n" + "WHERE\n" + "    leave_structure_details.lv_type_id =:lvTypeId AND leave_structure_details.del_status = 1 AND leave_structure_details.lvs_alloted_leaves > 0", nativeQuery = true)
public List<LeaveStructureDetails> findByLvTypeIdAndDelStatus(int lvTypeId)


}