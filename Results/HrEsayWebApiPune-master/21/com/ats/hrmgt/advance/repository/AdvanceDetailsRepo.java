import com.ats.hrmgt.model.advance.AdvanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AdvanceDetailsRepo extends JpaRepository<AdvanceDetails, Integer> {


@Query(value = " SELECT\n" + "    tbl_advance_details.adv_detail_id,\n" + "    tbl_advance_details.adv_id,\n" + "    tbl_advance_details.skip_login_id,\n" + "    tbl_advance_details.skip_login_time,\n" + "    tbl_advance_details.skip_remarks,\n" + "    tbl_advance_details.skip_remarks,\n" + "    tbl_advance_details.del_status,\n" + "    tbl_advance_details.ex_int1,\n" + "    tbl_advance_details.ex_int2,\n" + "    CONCAT(\n" + "        m_employees.first_name,\n" + "        ' ',\n" + "        m_employees.middle_name,\n" + "        ' ',\n" + "        m_employees.surname\n" + "    ) AS ex_var1,\n" + "    tbl_advance_details.ex_var2\n" + "FROM\n" + "    tbl_advance_details,\n" + "    m_employees\n" + "WHERE\n" + "    m_employees.emp_id=tbl_advance_details.skip_login_id AND tbl_advance_details.del_status=1", nativeQuery = true)
public List<AdvanceDetails> findAllByDelStatus()


public List<AdvanceDetails> findByAdvId(int advId)


}