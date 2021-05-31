import com.ats.hrmgt.model.ShiftAssignDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface ShiftAssignDailyRepository extends JpaRepository<ShiftAssignDaily, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE t_shift_assign_daily SET shift_id=:shiftId WHERE shift_date between :fromDate and :toDate and emp_id in (:empIdList)", nativeQuery = true)
public int updateAssignShiftByDate(List<Integer> empIdList,String fromDate,String toDate,int shiftId)


@Query(value = "select\n" + "        ds.id,ds.emp_id,ds.shift_id,ds.shift_date,ds.month,ds.year,gp.ex_int1 as extra1,ds.extra2,ds.var1,ds.var2,ds.emp_code\n" + "    from\n" + "        t_shift_assign_daily  ds,tbl_shift_timming st,m_self_grup gp\n" + "    where\n" + "        shift_date between :fromDate and :toDate and st.id=ds.shift_id and st.self_group_id=gp.selft_group_id", nativeQuery = true)
public List<ShiftAssignDaily> shiftAssignDailyList(String fromDate,String toDate)


@Query(value = "select\n" + "        ds.id,ds.emp_id,ds.shift_id,ds.shift_date,ds.month,ds.year,gp.ex_int1 as extra1,ds.extra2,ds.var1,ds.var2,ds.emp_code\n" + "    from\n" + "        t_shift_assign_daily  ds,tbl_shift_timming st,m_self_grup gp\n" + "    where\n" + "        shift_date between :fromDate and :toDate and st.id=ds.shift_id and st.self_group_id=gp.selft_group_id and ds.emp_id=:empId", nativeQuery = true)
public List<ShiftAssignDaily> shiftAssignDailyListById(String fromDate,String toDate,int empId)


}