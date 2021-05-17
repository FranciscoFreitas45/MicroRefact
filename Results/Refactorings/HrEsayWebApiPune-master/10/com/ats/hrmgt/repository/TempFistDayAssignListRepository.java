import com.ats.hrmgt.model.TempFistDayAssignList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface TempFistDayAssignListRepository extends JpaRepository<TempFistDayAssignList, Integer> {


@Query(value = "select tas.*,emp.first_name,emp.middle_name,emp.surname,emp.emp_code," + "IFNULL((select shiftname from tbl_shift_timming where id=tas.shift_id),'') as shift_name,\n" + "        IFNULL((select m_self_grup.name from tbl_shift_timming,m_self_grup where tbl_shift_timming.id=tas.shift_id and tbl_shift_timming.self_group_id=m_self_grup.selft_group_id),0) as shift_group_name,\n" + "        IFNULL((select m_self_grup.selft_group_id from tbl_shift_timming,m_self_grup where tbl_shift_timming.id=tas.shift_id and tbl_shift_timming.self_group_id=m_self_grup.selft_group_id),0) as shift_group,\n" + "        IFNULL((select m_self_grup.ex_int1 from tbl_shift_timming,m_self_grup where tbl_shift_timming.id=tas.shift_id and tbl_shift_timming.self_group_id=m_self_grup.selft_group_id),0) as shift_type" + " from t_temp_assign_first_day_shift tas,m_employees emp where emp.emp_id=tas.emp_id " + "and emp.location_id=:locId and tas.date=:date and emp.del_status=1 and emp.emp_id not in ( select sds.emp_id  from t_shift_assign_daily sds where " + "sds.shift_date=:date )", nativeQuery = true)
public List<TempFistDayAssignList> getFistDayAssignShiftFromTemp(String date,int locId)


}