import com.ats.hrmgt.model.GetWeekShiftChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetWeekShiftChangeRepo extends JpaRepository<GetWeekShiftChange, Integer> {


@Query(value = "SELECT\n" + "    tbl_weekoffshift.id,\n" + "    tbl_weekoffshift.month,\n" + "    tbl_weekoffshift.year,\n" + "    tbl_weekoffshift.weekofffromdate,\n" + "    tbl_weekoffshift.weekoffshiftdate,\n" + "    tbl_weekoffshift.cmp_id,\n" + "    tbl_weekoffshift.reason,\n" + "    tbl_weekoffshift.del_status,\n" + "    m_location.loc_name,tbl_weekoffshift.location_id,tbl_weekoffshift.login_time\n" + "FROM\n" + "    tbl_weekoffshift,\n" + "    m_location\n" + "WHERE\n" + "    tbl_weekoffshift.del_status = 1 AND tbl_weekoffshift.location_id = m_location.loc_id  AND  tbl_weekoffshift.year=:yearId AND  tbl_weekoffshift.month=:month ", nativeQuery = true)
public List<GetWeekShiftChange> getAllWeekShiftedAllEmp(String month,String yearId)


@Query(value = "SELECT\n" + "    tbl_weekoffshift.id,\n" + "    tbl_weekoffshift.month,\n" + "    tbl_weekoffshift.year,\n" + "    tbl_weekoffshift.weekofffromdate,\n" + "    tbl_weekoffshift.weekoffshiftdate,\n" + "    tbl_weekoffshift.cmp_id,\n" + "    tbl_weekoffshift.reason,\n" + "    tbl_weekoffshift.del_status,\n" + "    m_location.loc_name,tbl_weekoffshift.location_id,tbl_weekoffshift.login_time\n" + "FROM\n" + "    tbl_weekoffshift,\n" + "    m_location\n" + "WHERE\n" + "    tbl_weekoffshift.del_status = 1 AND tbl_weekoffshift.location_id = m_location.loc_id  AND  tbl_weekoffshift.year=:yearId AND  tbl_weekoffshift.month=:month AND  tbl_weekoffshift.emp_id=:empId", nativeQuery = true)
public List<GetWeekShiftChange> getAllWeekShifted(String month,String yearId,int empId)


}