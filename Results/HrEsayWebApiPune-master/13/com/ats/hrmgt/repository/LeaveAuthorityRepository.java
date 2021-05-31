import com.ats.hrmgt.model.LeaveAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface LeaveAuthorityRepository extends JpaRepository<LeaveAuthority, Integer> {


public LeaveAuthority findByLaPkeyAndDelStatus(int laPkey,int i)


public List<LeaveAuthority> findByDelStatus(int i)


public LeaveAuthority findByDelStatusAndEmpId(int i,int empId)


@Transactional
@Modifying
@Query("update LeaveAuthority set del_status=0  WHERE la_pkey=:laPkey")
public int deleteLeaveAuthority(int laPkey)


@Query(value = " SELECT\n" + "    *\n" + "FROM\n" + "    leave_authority\n" + "WHERE\n" + "    :empId IN(\n" + "        leave_authority.ini_auth_emp_id\n" + "    ) OR :empId IN(\n" + "        leave_authority.fin_auth_emp_id AND  leave_authority.del_status=1 \n" + "    )", nativeQuery = true)
public List<LeaveAuthority> chkAuth(int empId)


}