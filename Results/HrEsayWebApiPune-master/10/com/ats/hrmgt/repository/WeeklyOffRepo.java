import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.WeeklyOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface WeeklyOffRepo extends JpaRepository<WeeklyOff, Integer> {


public WeeklyOff findBywoIdAndDelStatus(int woId,int i)


public List<WeeklyOff> findByExInt1AndDelStatus(int hoCatId,int i)


@Query(value = " select weekend_category from m_employees   where emp_id=:empId", nativeQuery = true)
public int getweekendCatId(int empId)


public List<WeeklyOff> findByDelStatusAndIsActive(int i,int j)


public List<EmployeeMaster> findByExInt2AndDelStatus(int skillId,int i)


@Query(value = " select w.* from weekly_off w  where  w.del_status=1", nativeQuery = true)
public List<WeeklyOff> getWeeklyOffList()


@Query(value = " select w.* from weekly_off w  where  w.del_status=1 AND w.company_id=:companyId AND w.loc_id=:locId", nativeQuery = true)
public WeeklyOff getRecord(int companyId,int locId)


@Query(value = " select holiday_category from m_employees   where emp_id=:empId", nativeQuery = true)
public int getholidayCatId(int empId)


@Transactional
@Modifying
@Query("update WeeklyOff set del_status=0  WHERE wo_id=:woId")
public int deleteWeeklyOff(int woId)


@Query(value = " select w.* from weekly_off w, m_employees e where e.location_id=w.loc_id and e.emp_id=:empId and w.del_status=1", nativeQuery = true)
public List<WeeklyOff> getWeeklyOffListByEmpId(int empId)


}